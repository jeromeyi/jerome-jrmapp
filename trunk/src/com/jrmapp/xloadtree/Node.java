package com.jrmapp.xloadtree;

import java.util.ArrayList;
import java.util.List;


public class Node {
	private String text;
	private String action;
	private String src;
	private String icon;
	private String openIcon;
	private String target;
	private int checkboxType;
	private boolean  isChecked;
	private String value;
	private String oncontextmenu;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getSrc() {
		return src;
	}
	public void setSrc(String src) {
		this.src = src;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getOpenIcon() {
		return openIcon;
	}
	public void setOpenIcon(String openIcon) {
		this.openIcon = openIcon;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}

	public int getCheckboxType() {
		return checkboxType;
	}
	public void setCheckboxType(int checkboxType) {
		this.checkboxType = checkboxType;
	}
	public boolean getIsChecked() {
		return isChecked;
	}
	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public String getOncontextmenu() {
		return oncontextmenu;
	}
	public void setOncontextmenu(String oncontextmenu) {
		this.oncontextmenu = oncontextmenu;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
