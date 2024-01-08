package dk.dtu.app.view;

import javafx.scene.control.Button;

class MyButton extends Button {

	private int MyValue;

	public MyButton(int MyValue) {
		this.MyValue = MyValue;
	}

	// A method to get the value of a button
	public int getMyValue() {
		return MyValue;
	}

	// A method to change the value of a button
	public void setMyValue(int MyValue) {
		this.MyValue = MyValue;
	}
}
