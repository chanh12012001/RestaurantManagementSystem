package GUI.Component.Dashboard;

import DTO.MenuModel;
import GUI.TableManagerJFrame;
import Interface.EventMenuSelected;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;

public class MenuList<E extends Object> extends JList<E> {

    private final DefaultListModel model;
    public static int selectedIndex = 0;
    private final Dimension dimension;
    private EventMenuSelected event;
    
    public void addEventMenuSelected(EventMenuSelected event) {
        this.event = event;
    }
    
    public MenuList(Dimension dimension) {
        this.dimension = dimension;
                
        model = new DefaultListModel();
        setModel(model);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent me) {
                if (SwingUtilities.isLeftMouseButton(me)) {
                    int index = locationToIndex(me.getPoint());
                    Object o = model.getElementAt(index);
                    if (o instanceof MenuModel menu) {
                        if (menu.getType() == MenuModel.MenuType.MENU) {
                            selectedIndex = index;
                            if (event != null) {
                                event.selected(index);
                            }
                        }
                    } else {
                        selectedIndex = index;
                    }
                    repaint();
                }
            }
        });
    } 

    @Override
    public ListCellRenderer<? super E> getCellRenderer() {
        return new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> jlist, Object o, int index, boolean selected, boolean focus) {
                MenuModel data;
                if (o instanceof MenuModel menuModel) {
                    data = menuModel;
                } else {
                    data = new MenuModel("", o + "", MenuModel.MenuType.EMPTY);
                }
                MenuItem item = new MenuItem(data, dimension);
                item.setSelected(selectedIndex == index);
                return item;
            }

        };
    }

    public void addItem(MenuModel data) {
        model.addElement(data);
    }
    
    
      // Variables declaration - do not modify                     
    private GUI.Component.Dashboard.BodyLayout bodyPanel;
    // End of variables declaration   
}