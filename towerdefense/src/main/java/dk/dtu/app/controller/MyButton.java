package dk.dtu.app.controller;

import javafx.scene.control.Button;

// Modified button class
    public class MyButton extends Button {


        // values:
        // 0 = Tower field
        // 1 = Path
        
        public int value;

        public MyButton(int value) {
            this.value = value;
        }

        public void setValue(int newValue) {
            this.value = newValue;
        }
        public int getValue(){
            return this.value;
        }

    }