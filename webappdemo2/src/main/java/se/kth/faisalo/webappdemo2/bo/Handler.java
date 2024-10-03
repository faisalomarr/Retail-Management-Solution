package se.kth.faisalo.webappdemo2.bo;

import se.kth.faisalo.webappdemo2.ui.ItemInfo;

import java.util.ArrayList;
import java.util.List;

public class Handler {

    public static List<ItemInfo> getItems() {
        List<Item> items = Item.searchItems();
        List<ItemInfo> itemInfos = new ArrayList<>();
        for (Item item : items) {
            itemInfos.add(new ItemInfo(item.getName(),item.getDescription()));
        }
        return itemInfos;

    }
}
