package edu.utn.frba.dds.domain;

import java.util.ArrayList;
import java.util.List;

public class Remito {
    List<Item> items = new ArrayList();

    public Remito(List<Item> items) {
        this.items = items;

    }
}
