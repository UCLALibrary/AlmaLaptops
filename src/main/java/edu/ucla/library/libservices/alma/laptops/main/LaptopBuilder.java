
package edu.ucla.library.libservices.alma.laptops.main;

import edu.ucla.library.libservices.alma.laptops.beans.AlmaItem;
import edu.ucla.library.libservices.alma.laptops.beans.AlmaMember;
import edu.ucla.library.libservices.alma.laptops.beans.AvailableItems;
import edu.ucla.library.libservices.alma.laptops.beans.MemberList;
import edu.ucla.library.libservices.alma.laptops.clients.ItemClient;
import edu.ucla.library.libservices.alma.laptops.clients.MemberClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class LaptopBuilder {

    public LaptopBuilder() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        final List<AlmaItem> availableItems;
        final List<AlmaItem> items = new ArrayList<AlmaItem>();
        final List<AvailableItems> locsWithCounts = new ArrayList<AvailableItems>();
        final MemberClient client = new MemberClient();
        final Set<String> locations = new TreeSet<String>();

        client.setBaseURL("https://api-na.hosted.exlibrisgroup.com/almaws/v1/conf/sets/");
        client.setKey("l8xxf5a239d1e9174c7c908fe163965d04fb");
        client.setSetID("2580289030006533");
        client.setLimit("100");
        client.setOffset("0");

        System.out.println("Starting at " + new Date());
        MemberList records = client.getRecords();
        addItems(records, items);
        if (records.getRecordCount() > 100) {
            final int dividend = records.getRecordCount() / 100;
            for (int loop = 1; loop <= dividend; loop++) {
                final int offset = (loop * 100);
                client.setOffset(String.valueOf(offset));
                records = client.getRecords();
                addItems(records, items);
            }
        }
        availableItems =
                items.stream().filter(f -> f.getTheItem().getStatus().equals("1")).collect(Collectors.toList());
        availableItems.stream().forEach(i -> locations.add(i.getTheItem().getLocation()));
        locations.stream().forEach(e -> locsWithCounts.add(makeNewAvailableItem(e)));
        locsWithCounts.stream().forEach(e -> setCounts(e, availableItems));
        for (AvailableItems availables : locsWithCounts) {
            System.out.println(availables.getLocation() + "\tchrome: " + availables.getChromeBooks() + "\tmacs: " +
                    availables.getMacs() + "\tPCs: " + availables.getWindows() + "\tiPads: " + availables.getIPads());
        }
    }

    private static void addItems(MemberList records, List<AlmaItem> items) {
        for (AlmaMember theRecord : records.getRecords()) {
            ItemClient client = new ItemClient();
            client.setKey("l8xxf5a239d1e9174c7c908fe163965d04fb");
            client.setLink(theRecord.getLink());
            AlmaItem item = client.getItem();
            items.add(item);
        }
    }

    private static AvailableItems makeNewAvailableItem(String aLocation) {
        AvailableItems bean = new AvailableItems();
        bean.setLocation(aLocation);
        return bean;
    }

    private static void setCounts(AvailableItems aAvailableItem, List<AlmaItem> aItemsList) {
        List<AlmaItem> locItems =
                aItemsList.stream().filter(f -> f.getTheItem().getLocation().equals(aAvailableItem.getLocation()))
                        .collect(Collectors.toList());
        for (AlmaItem theLocItem : locItems) {
            if (theLocItem.getTheItem().getType().equals("ccipad")) {
                aAvailableItem.setIPads(aAvailableItem.getIPads() + 1);
            } else if (theLocItem.getTheItem().getType().equals("cclaptop")) {
                if (theLocItem.getTheItem().getBarcode().startsWith("CBK")) {
                    aAvailableItem.setChromeBooks(aAvailableItem.getChromeBooks() + 1);
                } else if ((theLocItem.getTheItem().getBarcode().startsWith("MP"))) {
                    aAvailableItem.setMacs(aAvailableItem.getMacs() + 1);
                } else if ((theLocItem.getTheItem().getBarcode().startsWith("HP"))) {
                    aAvailableItem.setWindows(aAvailableItem.getWindows() + 1);
                } else {
                    System.out.println(
                            theLocItem.getTheItem().getBarcode() + " has type " + theLocItem.getTheItem().getType());
                }
            }
        }
        aAvailableItem.setLocation(aAvailableItem.getLocation().concat(" Lending"));
    }
}
