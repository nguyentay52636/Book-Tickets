        package org.example.GUI.Components.FormHoaDon;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
        import javax.swing.JComboBox;
        import javax.swing.JComponent;
        import javax.swing.JLabel;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JTable;
        import javax.swing.JTextField;
        import javax.swing.ListSelectionModel;
        import javax.swing.OverlayLayout;
        import javax.swing.SwingConstants;
        import javax.swing.border.TitledBorder;
        import javax.swing.table.DefaultTableModel;

        import org.example.UtilsDate.FormattedDatePicker;

        import java.awt.BorderLayout;
        import java.awt.Color;
        import java.awt.Dimension;
        import java.awt.FlowLayout;
        import java.awt.Font;
        import java.awt.GridLayout;
        import java.util.Date;

        public class FormHoaDon extends JPanel {
            private final Font FONT_LABEL = new Font("Segoe UI", Font.PLAIN, 16);
            private final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 19);

            private final int HGAP = 11;
            private final int VGAP = 11;
            private final int BUTTON_WIDTH = 125;
            private final int LABEL_PREFERRED_WIDTH = 125;
            private final int TEXTFIELD_COLUMNS = 17;
            private final int PADDING = 7;

            private JPanel topContainer;

            private JPanel centerContainer;
            private DefaultTableModel invoiceTableModel;
            private JTable invoiceTable;
            private JScrollPane sp;
            private JLabel emptyLabel;

            private JPanel bottomContainer;
            private JButton exportExcelBtn;

            private JPanel infoPanel;
            private JLabel lblInvoiceID, lblCustomer, lblEmployee, lblDateTime, lblTotal;
            private JButton invoiceDetailBtn;

            private JPanel filterPanel;
            private JTextField txtInvoiceID, txtCustomerID, txtEmployeeID;
            private JComboBox<String> cbxDateCb;
            private FormattedDatePicker fromDatePicker, toDatePicker;
            private JPanel customDateContainer, buttonContainer;
            private JButton applyFilterBtn, resetFilterBtn;

                public FormHoaDon(String title) {
                setLayout(new BorderLayout(HGAP, VGAP));
                setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
                initComponents();
            }

            private void initComponents() {
                initInfoPanel();
                initFilterPanel();
                initTopContainer();
                initCenterContainer();
                initBottomContainer();

                add(topContainer, BorderLayout.NORTH);
                add(centerContainer, BorderLayout.CENTER);
                add(bottomContainer, BorderLayout.SOUTH);

                setListeners();
            }

            private void initTopContainer() {
                topContainer = new JPanel(new BorderLayout(HGAP, VGAP));
                topContainer.add(infoPanel, BorderLayout.WEST);
                topContainer.add(filterPanel, BorderLayout.CENTER);
            }

            private void initCenterContainer() {
                centerContainer = new JPanel();
                centerContainer.setLayout(new OverlayLayout(centerContainer));
                String[] columnsNames = { "Mã hoá đơn", "Mã khách hàng", "Mã nhân viên", "Ngày lập", "Giờ lập", "Tổng tiền" };
                invoiceTableModel = new DefaultTableModel(columnsNames, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                invoiceTable = new JTable(invoiceTableModel);
                invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                sp = new JScrollPane(invoiceTable);

                emptyLabel = new JLabel("DANH SÁCH TRỐNG", SwingConstants.CENTER);
                emptyLabel.setFont(new Font("SansSerif", Font.ITALIC, 32));
                emptyLabel.setForeground(Color.GRAY);
                emptyLabel.setAlignmentX(0.5f);
                emptyLabel.setAlignmentY(0.5f);

                centerContainer.add(emptyLabel);
                centerContainer.add(sp);

                styleTable();
                emptyLabel.setVisible(true);
            }

            private void initBottomContainer() {
                bottomContainer = new JPanel(new FlowLayout(FlowLayout.TRAILING));
                exportExcelBtn = new JButton("Xuất Excel");
                exportExcelBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, invoiceDetailBtn.getPreferredSize().height));
                exportExcelBtn.setBackground(new Color(40, 167, 69));
                exportExcelBtn.setForeground(Color.WHITE);
                exportExcelBtn.setFocusPainted(false);
                bottomContainer.add(exportExcelBtn);
            }

            private void initInfoPanel() {
                infoPanel = new JPanel(new GridLayout(6, 1));
                infoPanel.setPreferredSize(new Dimension(395, 0));
                infoPanel.setBorder(BorderFactory.createCompoundBorder(
                        createTitledBorder("Thông tin hoá đơn"),
                        BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)));

                lblInvoiceID = new JLabel("________");
                lblInvoiceID.setFont(FONT_LABEL);

                lblCustomer = new JLabel("________");
                lblCustomer.setFont(FONT_LABEL);

                lblEmployee = new JLabel("________");
                lblEmployee.setFont(FONT_LABEL);

                lblDateTime = new JLabel("________");
                lblDateTime.setFont(FONT_LABEL);

                lblTotal = new JLabel("________");
                lblTotal.setFont(FONT_LABEL);

                invoiceDetailBtn = new JButton("Xem chi tiết");
                invoiceDetailBtn.setFocusPainted(false);
                invoiceDetailBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, invoiceDetailBtn.getPreferredSize().height));
                invoiceDetailBtn.setBackground(new Color(0, 123, 255));
                invoiceDetailBtn.setForeground(Color.WHITE);
                invoiceDetailBtn.setIcon(loadImageIcon("/org/example/GUI/resources/images/view.png"));

                infoPanel.add(createRow(createLabel("Mã hoá đơn: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), lblInvoiceID, FlowLayout.LEADING));
                infoPanel.add(createRow(createLabel("Khách hàng: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), lblCustomer, FlowLayout.LEADING));
                infoPanel.add(createRow(createLabel("Nhân viên: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), lblEmployee, FlowLayout.LEADING));
                infoPanel.add(createRow(createLabel("Thời gian lập: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), lblDateTime, FlowLayout.LEADING));
                infoPanel.add(createRow(createLabel("Tổng tiền: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), lblTotal, FlowLayout.LEADING));
                infoPanel.add(createRow(null, invoiceDetailBtn, FlowLayout.TRAILING));
            }

            private void initFilterPanel() {
                filterPanel = new JPanel(new GridLayout(6, 1, 0, 5));
                filterPanel.setBorder(BorderFactory.createCompoundBorder(
                        createTitledBorder("Bộ lọc"),
                        BorderFactory.createEmptyBorder(PADDING, PADDING, PADDING, PADDING)));

                txtInvoiceID = new JTextField(TEXTFIELD_COLUMNS);
                txtCustomerID = new JTextField(TEXTFIELD_COLUMNS);
                txtEmployeeID = new JTextField(TEXTFIELD_COLUMNS);

                String[] dateItems = {
                        "Tất cả", "Hôm nay", "Hôm qua", "Trong 7 ngày", "Trong 31 ngày",
                        "Tuần này", "Tháng này", "Năm nay", "Tuỳ chọn"
                };
                cbxDateCb = new JComboBox<>(dateItems);
                cbxDateCb.setPreferredSize(txtCustomerID.getPreferredSize());

                Date today = new Date();
                fromDatePicker = new FormattedDatePicker(today);
                toDatePicker = new FormattedDatePicker(today);
                customDateContainer = new JPanel(new FlowLayout(FlowLayout.LEADING));
                buttonContainer = new JPanel(new FlowLayout(FlowLayout.LEADING, 5, 0));

                customDateContainer.add(new JLabel("Từ ngày"));
                customDateContainer.add(fromDatePicker);
                customDateContainer.add(new JLabel("đến ngày"));
                customDateContainer.add(toDatePicker);
                customDateContainer.setVisible(false);

                applyFilterBtn = new JButton("Tìm kiếm");
                applyFilterBtn.setPreferredSize(new Dimension(BUTTON_WIDTH, invoiceDetailBtn.getPreferredSize().height));
                applyFilterBtn.setFocusPainted(false);
                applyFilterBtn.setIcon(loadImageIcon("/org/example/GUI/resources/images/glass-icon.png"));

                resetFilterBtn = new JButton("Xoá bộ lọc");
                resetFilterBtn.setPreferredSize(new Dimension(BUTTON_WIDTH - 35, invoiceDetailBtn.getPreferredSize().height));
                resetFilterBtn.setFocusPainted(false);
                resetFilterBtn.setBackground(new Color(220, 53, 69));
                resetFilterBtn.setForeground(Color.WHITE);
                resetFilterBtn.setIcon(loadImageIcon("/org/example/GUI/resources/images/bin.png"));

                buttonContainer.add(applyFilterBtn);
                buttonContainer.add(resetFilterBtn);

                filterPanel.add(createRow(createLabel("Mã hoá đơn: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), txtInvoiceID, FlowLayout.LEADING));
                filterPanel.add(createRow(createLabel("Mã khách hàng: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), txtCustomerID, FlowLayout.LEADING));
                filterPanel.add(createRow(createLabel("Mã nhân viên: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), txtEmployeeID, FlowLayout.LEADING));
                filterPanel.add(createRow(createLabel("Chọn thời gian: ", LABEL_PREFERRED_WIDTH, FONT_LABEL), cbxDateCb, FlowLayout.LEADING));
                filterPanel.add(customDateContainer);
                filterPanel.add(buttonContainer);
            }

            private void setListeners() {
                applyFilterBtn.addActionListener(e -> { /* chỉ giao diện */ });

                resetFilterBtn.addActionListener(e -> {
                    txtInvoiceID.setText("");
                    txtCustomerID.setText("");
                    txtEmployeeID.setText("");
                    cbxDateCb.setSelectedIndex(0);
                    customDateContainer.setVisible(false);
                });

                exportExcelBtn.addActionListener(e -> { /* chỉ giao diện */ });

                invoiceDetailBtn.addActionListener(e -> { /* chỉ giao diện */ });

                cbxDateCb.addActionListener(e -> {
                    if ("Tuỳ chọn".equals(cbxDateCb.getSelectedItem())) {
                        customDateContainer.setVisible(true);
                    } else {
                        customDateContainer.setVisible(false);
                    }
                });

                invoiceTable.getSelectionModel().addListSelectionListener(e -> {
                    if (invoiceTable.getSelectedRow() >= 0) {
                        lblInvoiceID.setText("________");
                        lblCustomer.setText("________");
                        lblEmployee.setText("________");
                        lblDateTime.setText("________");
                        lblTotal.setText("________");
                    }
                });
            }

            private void styleTable() {
                invoiceTable.setRowHeight(35);
                invoiceTable.setShowGrid(true);
                invoiceTable.setGridColor(new Color(230, 230, 230));
                invoiceTable.setFont(FONT_LABEL);
                invoiceTable.getTableHeader().setFont(FONT_TITLE);
                invoiceTable.getTableHeader().setBackground(new Color(70, 130, 180));
                invoiceTable.getTableHeader().setForeground(Color.WHITE);
            }

            private TitledBorder createTitledBorder(String title) {
                TitledBorder border = BorderFactory.createTitledBorder(title);
                border.setTitleFont(FONT_TITLE);
                return border;
            }

            private JPanel createRow(JLabel label, JComponent component, int rowAlignment) {
                JPanel panel = new JPanel(new FlowLayout(rowAlignment));
                if (label != null)
                    panel.add(label);
                panel.add(component);
                return panel;
            }

            private JLabel createLabel(String text, int width, Font font) {
                JLabel lbl = new JLabel(text);
                if (font != null)
                    lbl.setFont(font);
                lbl.setHorizontalAlignment(JLabel.LEADING);
                lbl.setPreferredSize(new Dimension(width, lbl.getPreferredSize().height));
                return lbl;
            }

            private ImageIcon loadImageIcon(String path) {
                java.net.URL imgURL = getClass().getResource(path);
                if (imgURL != null) {
                    return new ImageIcon(imgURL);
                } else {
                    System.err.println("Không thể tải hình ảnh: " + path);
                    return null;
                }
            }
        }
