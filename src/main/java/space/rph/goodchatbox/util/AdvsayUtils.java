/*
    DO NOT MODIFY THIS FILE UNLESS YOU ARE 100% SURE WHAT YOU ARE DOING
    I DONT EXACTLY KNOW WHY IT WORKS
    BUT IT DOES
 */


package space.rph.goodchatbox.util;

import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumChatFormatting;
import space.rph.goodchatbox.util.AdvsayParsingException;

import li.cil.repack.com.naef.jnlua.util.AbstractTableMap;
import net.minecraft.util.ChatComponentText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AdvsayUtils {
    private String[] allowedColors = {"0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"};
    private Map args;
    private double lengthBudget;
    public ChatComponentText full;
    public AdvsayUtils(Map args) {
        this.lengthBudget = 2048;
        this.args = args;
    }
    public void buildFromMap() throws AdvsayParsingException {
        this.full = new ChatComponentText("");
        List<Object> ids = new ArrayList<Object>(this.args.keySet());

        for (Object element : ids) {
            ChatComponentText temp;
            TextPart e = new TextPart((AbstractTableMap<Object>) this.args.get(element), true);
            temp = e.component.createCopy();
            lengthBudget = lengthBudget - e.getLength();
            full.appendSibling(temp);
        }
        if (lengthBudget < 0) {
            throw new AdvsayParsingException("Message length budget exhausted");
        }
    }

    private class ClickActionTab {
        public ClickEvent event;

        public ClickActionTab(AbstractTableMap<Object> part) throws AdvsayParsingException {
            ClickEvent.Action action;
            String rawAction = (String) part.get("action");
            String value = (String) part.get("value");
            if (rawAction == null || value == null) {
                throw new AdvsayParsingException("ClickActionTab received no action or value");
            }
            if (rawAction.equals("open_url")) {
                action = ClickEvent.Action.OPEN_URL;
            } else if (rawAction.equals("suggest_command")) {
                action = ClickEvent.Action.SUGGEST_COMMAND;
            } else {
                throw new AdvsayParsingException("ClickActionTab received bad action");
            }
            this.event = new ClickEvent(action, value);
        }
    }

    private class ActionTab {
        public ClickEvent clickEvent;
        public HoverEvent hoverEvent;

        public ActionTab(AbstractTableMap<Object> part) throws AdvsayParsingException {
            Object click = part.get("click");
            if (click != null) {
                ClickActionTab c = new ClickActionTab((AbstractTableMap<Object>) click);
                clickEvent = c.event;
            }
            Object hover = part.get("hover");
            if (hover != null) {
                TextPart textPart = new TextPart((AbstractTableMap<Object>) hover, false);
                hoverEvent = new HoverEvent(HoverEvent.Action.SHOW_TEXT, textPart.component);
            }
        }
    }

    private class TextPart {
        public ChatComponentText component;
        private double length;
        public TextPart(AbstractTableMap<Object> part, boolean followAction) throws AdvsayParsingException {
            String text = (String) part.get("text");
            String color = (String) part.get("color");
            ActionTab tab = null;
            Boolean bold = (Boolean) part.get("bold");
            Boolean italic = (Boolean) part.get("italic");
            Boolean strikethrough = (Boolean) part.get("strikethrough");
            Boolean underlined = (Boolean) part.get("underlined");
            Boolean magic = (Boolean) part.get("obfuscated");

            color = (color != null) ? color : "f";
            color = color.toLowerCase();

            bold = (bold != null) ? bold : false;
            italic = (italic != null) ? italic : false;
            strikethrough = (strikethrough != null) ? strikethrough : false;
            underlined = (underlined != null) ? underlined : false;
            magic = (magic != null) ? magic : false;

            if (text == null) {
                throw new AdvsayParsingException("No text passed to TextPart");
            }

            if (!Arrays.asList(allowedColors).contains(color)) {
                throw new AdvsayParsingException("Invalid color passed to TextPart");
            }

            if (followAction) {
                Object action = part.get("actions");
                if (action != null) {
                    tab = new ActionTab((AbstractTableMap<Object>) action);
                }
            }
            EnumChatFormatting realColor;
            switch(color.charAt(0)) {
                case '0':
                    realColor = EnumChatFormatting.BLACK;
                    break;
                case '1':
                    realColor = EnumChatFormatting.DARK_BLUE;
                    break;
                case '2':
                    realColor = EnumChatFormatting.DARK_GREEN;
                    break;
                case '3':
                    realColor = EnumChatFormatting.DARK_AQUA;
                    break;
                case '4':
                    realColor = EnumChatFormatting.DARK_RED;
                    break;
                case '5':
                    realColor = EnumChatFormatting.DARK_PURPLE;
                    break;
                case '6':
                    realColor = EnumChatFormatting.GOLD;
                    break;
                case '7':
                    realColor = EnumChatFormatting.GRAY;
                    break;
                case '8':
                    realColor = EnumChatFormatting.DARK_GRAY;
                    break;
                case '9':
                    realColor = EnumChatFormatting.BLUE;
                    break;
                case 'a':
                    realColor = EnumChatFormatting.GREEN;
                    break;
                case 'b':
                    realColor = EnumChatFormatting.AQUA;
                    break;
                case 'c':
                    realColor = EnumChatFormatting.RED;
                    break;
                case 'd':
                    realColor = EnumChatFormatting.LIGHT_PURPLE;
                    break;
                case 'e':
                    realColor = EnumChatFormatting.YELLOW;
                    break;
                default:
                    realColor = EnumChatFormatting.WHITE;
                    break;

            }
            // Build the ChatComponentText, at last
            ChatStyle style = new ChatStyle();
            style.setBold(bold);
            style.setItalic(italic);
            style.setStrikethrough(strikethrough);
            style.setUnderlined(underlined);
            style.setObfuscated(magic);
            style.setColor(realColor);
            if (followAction) {
                if (tab != null) {
                    if (tab.hoverEvent != null) {
                        style.setChatHoverEvent(tab.hoverEvent);
                    }
                    if (tab.clickEvent != null) {
                        style.setChatClickEvent(tab.clickEvent);
                    }
                }
            }
            length = text.length();
            component = new ChatComponentText(text);
            component.setChatStyle(style);
        }
        public double getLength() {
            return this.length;
        }
    }
}
