package dk.dtu.app.controller.BoardLogic;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.Pane;

public class MyPane extends Pane {
    private Map<String, Integer> map;

    public MyPane() {
        this.map = new HashMap<>();
    }

    public void setHashMap(Map<String, Integer> hashmap) {
        this.map = hashmap;
    }

    public Map<String, Integer> getHashMap() {
        return this.map;
    }
}