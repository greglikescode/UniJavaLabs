package openworld.gui;

import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;

import openworld.adventurer.Inventory;
import openworld.item.ConsumableItem;
import openworld.item.EquippableItem;
import openworld.item.InventorySlotType;

public class InventoryPanel extends JPanel implements ActionListener {

    private Map<InventorySlotType, JButton> equipButtons;
    private Map<InventorySlotType, JList<EquippableItem>> equipLists;
    private JButton consumeButton;
    private JList<ConsumableItem> consumeList;
    private Inventory inventory;
    private ControlPanel controlPanel;

    public InventoryPanel(Inventory inventory, ControlPanel controlPanel) {
        this.inventory = inventory;
        this.controlPanel = controlPanel;

        setBorder(new TitledBorder("Inventory management"));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        equipButtons = new HashMap<>();
        equipLists = new HashMap<>();

        for (InventorySlotType type : InventorySlotType.values()) {
            JList<EquippableItem> equipList = createList(new EquippableListModel(inventory, type));
            equipLists.put(type, equipList);
            add(addTitleAndScroll(equipList, type.toString()));
            JButton equipButton = new JButton("Equip " + type);
            equipButton.addActionListener(this);
            equipButtons.put(type, equipButton);
            add(equipButton);
            add(Box.createVerticalGlue());
        }

        consumeList = createList(new ConsumableListModel(inventory));
        add(addTitleAndScroll(consumeList, "CONSUMABLES"));
        consumeButton = new JButton("Consume");
        consumeButton.addActionListener(this);
        add(consumeButton);
    }

    private <T> JList<T> createList(ListModel<T> listModel) {
        JList<T> list = new JList<T>(listModel);
        list.setVisibleRowCount(3);
        return list;
    }

    private JScrollPane addTitleAndScroll(JList<?> list, String title) {
        JScrollPane listScroll = new JScrollPane(list);
        listScroll.setBorder(new TitledBorder(title));
        return listScroll;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // e.getSource() corresponds to what button was clicked.
            Object source = e.getSource();
            if (source == consumeButton) {
                int consumeSelected = (source == consumeButton) ? consumeList.getSelectedIndex() : -1;
                if (consumeSelected != -1) {
                    inventory.useItem(consumeSelected);
                    System.out.println("Consumable was used!!!");
                } else {
                    System.out.println("No Consumable was selected!!!");
                }
            }

            // Loops through all the keys of a dictionary which is "Type"
            for (InventorySlotType type : equipButtons.keySet()) {
                if (e.getSource() == equipButtons.get(type)) {
                    switch (type) {
                        case ARMOUR:
                            EquippableItem armourSel = equipLists.get(type).getSelectedValue();
                            inventory.equipItem(armourSel);
                        case SWORD:
                            EquippableItem swordSel = equipLists.get(type).getSelectedValue();
                            inventory.equipItem(swordSel);
                    }
                }
            }
        } catch (Exception error) {
        }

            
        updateLists();
        controlPanel.getAdventurerPanel().update();
    }

    public void updateLists() {
        ((ConsumableListModel) consumeList.getModel()).update();
        for (InventorySlotType type : InventorySlotType.values()) {
            ((EquippableListModel) equipLists.get(type).getModel()).update();
        }
    }

    public void disableAll() {
        consumeButton.setEnabled(false);
        consumeList.setEnabled(false);
        for (InventorySlotType type : InventorySlotType.values()) {
            equipButtons.get(type).setEnabled(false);
            equipLists.get(type).setEnabled(false);
        }
    }

}
