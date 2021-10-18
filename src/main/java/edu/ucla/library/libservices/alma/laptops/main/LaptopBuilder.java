
package edu.ucla.library.libservices.alma.laptops.main;

import edu.ucla.library.libservices.alma.laptops.beans.AlmaItem;
import edu.ucla.library.libservices.alma.laptops.beans.AlmaMember;
import edu.ucla.library.libservices.alma.laptops.beans.AvailableItems;
import edu.ucla.library.libservices.alma.laptops.beans.MemberList;
import edu.ucla.library.libservices.alma.laptops.clients.ItemClient;
import edu.ucla.library.libservices.alma.laptops.clients.MemberClient;
import edu.ucla.library.libservices.alma.laptops.db.DataHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LaptopBuilder {

	final static Logger logger = LogManager.getLogger( LaptopBuilder.class );
    private static Properties props;

    public LaptopBuilder() {
       super();
    }

    public static void main(String[] args) {
        final List<AlmaItem> availableItems;
        final List<AlmaItem> items = new ArrayList<AlmaItem>();
        final List<AvailableItems> locsWithCounts = new ArrayList<AvailableItems>();
        final MemberClient client = new MemberClient();
        final Set<String> locations = new TreeSet<String>();

        loadProperties(args[0]);

        client.setBaseURL(props.getProperty("alma.url"));
        client.setKey(props.getProperty("alma.key"));
        client.setSetID(props.getProperty("alma.set"));
        client.setLimit("100");
        client.setOffset("0");

        logger.info("Starting at " + new Date());
        MemberList records = client.getRecords();
        addItems(records, items);
        if (records.getTotal_record_count() > 100) {
            final int dividend = records.getTotal_record_count() / 100;
            for (int loop = 1; loop <= dividend; loop++) {
                final int offset = (loop * 100);
                client.setOffset(String.valueOf(offset));
                records = client.getRecords();
                addItems(records, items);
            }
        }
        availableItems =
                items.stream().filter(f -> f.getItem_data() != null && f.getItem_data().getBase_status().getValue().equals("1")).collect(Collectors.toList());
        availableItems.stream().forEach(i -> locations.add(i.getItem_data().getLibrary().getValue()));
        locations.stream().forEach(e -> locsWithCounts.add(makeNewAvailableItem(e)));
        locsWithCounts.stream().forEach(e -> setCounts(e, availableItems));
        logger.info("Ending at " + new Date());
        DataHandler.clearRows(props);
        DataHandler.addRows(locsWithCounts, props);
    }

	private static void loadProperties(String propFile)
    {
		props = new Properties();
		try {
			props.load(new FileInputStream(new File(propFile)));
		} catch (IOException details) {
			logger.fatal("properties file problem: " + details.getMessage());
			System.exit(-1);
		}
  }

    private static void addItems(MemberList records, List<AlmaItem> items) {
        if (records != null && records.getMember() != null ) {
            for (AlmaMember theRecord : records.getMember()) {
                    ItemClient client = new ItemClient();
			        client.setKey(props.getProperty("alma.key"));
                    client.setLink(theRecord.getLink());
                    AlmaItem item = client.getItem();
                    items.add(item);
            }
		}
    }

    private static AvailableItems makeNewAvailableItem(String aLocation) {
        AvailableItems bean = new AvailableItems();
        bean.setLocation(aLocation);
        return bean;
    }

    private static void setCounts(AvailableItems aAvailableItem, List<AlmaItem> aItemsList) {
        List<AlmaItem> locItems =
                aItemsList.stream().filter(f -> f.getItem_data().getLibrary().getValue().equals(aAvailableItem.getLocation()))
                        .collect(Collectors.toList());
        for (AlmaItem theLocItem : locItems) {
            if (theLocItem.getItem_data().getPolicy().getValue().equals(props.getProperty("alma.tablet"))) {
                aAvailableItem.setIPads(aAvailableItem.getIPads() + 1);
            } else if (theLocItem.getItem_data().getPolicy().getValue().equals(props.getProperty("alma.laptop"))) {
                if (theLocItem.getItem_data().getBarcode().startsWith(props.getProperty("vger.chrome"))) {
                    aAvailableItem.setChromeBooks(aAvailableItem.getChromeBooks() + 1);
                } else if ((theLocItem.getItem_data().getBarcode().startsWith(props.getProperty("vger.mac")))) {
                    aAvailableItem.setMacs(aAvailableItem.getMacs() + 1);
                } else if ((theLocItem.getItem_data().getBarcode().startsWith(props.getProperty("vger.pc")))) {
                    aAvailableItem.setWindows(aAvailableItem.getWindows() + 1);
                } else {
                    logger.info(
                            theLocItem.getItem_data().getBarcode() + " has type " + theLocItem.getItem_data().getPolicy().getValue());
                }
            }
        }
        aAvailableItem.setLocation(aAvailableItem.getLocation().concat(" Lending"));
    }
}
