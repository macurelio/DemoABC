package com.example.crudabctestdos;

import com.example.crudabctestdos.model.Item;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "item")
public class ItemList {


        private List<Item> items;

        public ItemList() {}

        public ItemList(List<Item> items) {
            this.items = items;
        }

        @XmlElement(name = "item")
        public List<Item> getItems() {
            return items;
        }

        public void setItems(List<Item> items) {
            this.items = items;
        }
    }


