package grafica;

import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.LayoutManager2;
import java.awt.Rectangle;
import java.util.IdentityHashMap;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;

final class WindowLayout {
    private static final Dimension SIZE_720P = new Dimension(1280, 720);
    private static final double MAX_FONT_SCALE = 1.3;

    private WindowLayout() {
    }

    static void configure(JFrame window) {
        Container contentPane = window.getContentPane();
        LayoutManager originalLayout = contentPane.getLayout();
        Dimension designSize = originalLayout.preferredLayoutSize(contentPane);
        if (designSize.width <= 0 || designSize.height <= 0) {
            designSize = contentPane.getSize();
        }

        ScaledLayout scaledLayout = new ScaledLayout(originalLayout, designSize);
        scaledLayout.captureTextComponents(contentPane);
        contentPane.setLayout(scaledLayout);

        window.setResizable(false);
        window.setPreferredSize(SIZE_720P);
        window.setMinimumSize(SIZE_720P);
        window.setMaximumSize(SIZE_720P);
        window.setSize(SIZE_720P);
        window.validate();
        window.setLocationRelativeTo(null);
    }

    private static final class ScaledLayout implements LayoutManager2 {
        private final LayoutManager delegate;
        private final Dimension designSize;
        private final Map<Component, Font> originalFonts = new IdentityHashMap<>();
        private final Map<JTable, Integer> originalRowHeights = new IdentityHashMap<>();

        private ScaledLayout(LayoutManager delegate, Dimension designSize) {
            this.delegate = delegate;
            this.designSize = new Dimension(designSize);
        }

        private void captureTextComponents(Container root) {
            captureTextComponents((Component) root);
        }

        private void captureTextComponents(Component component) {
            Font font = component.getFont();
            if (font != null) {
                originalFonts.putIfAbsent(component, font);
                if (component instanceof JLabel label) {
                    label.setHorizontalAlignment(JLabel.CENTER);
                }
                if (component instanceof JTable table) {
                    originalRowHeights.putIfAbsent(table, table.getRowHeight());
                }
            }
            if (component instanceof Container container) {
                for (Component child : container.getComponents()) {
                    captureTextComponents(child);
                }
            }
        }

        @Override
        public void addLayoutComponent(Component component, Object constraints) {
            if (delegate instanceof LayoutManager2 layoutManager) {
                layoutManager.addLayoutComponent(component, constraints);
            } else {
                delegate.addLayoutComponent(constraints == null ? null : constraints.toString(), component);
            }
        }

        @Override
        public void addLayoutComponent(String name, Component component) {
            delegate.addLayoutComponent(name, component);
        }

        @Override
        public void removeLayoutComponent(Component component) {
            delegate.removeLayoutComponent(component);
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            return delegate.preferredLayoutSize(parent);
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            return delegate.minimumLayoutSize(parent);
        }

        @Override
        public Dimension maximumLayoutSize(Container target) {
            if (delegate instanceof LayoutManager2 layoutManager) {
                return layoutManager.maximumLayoutSize(target);
            }
            return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
        }

        @Override
        public float getLayoutAlignmentX(Container target) {
            if (delegate instanceof LayoutManager2 layoutManager) {
                return layoutManager.getLayoutAlignmentX(target);
            }
            return 0.5f;
        }

        @Override
        public float getLayoutAlignmentY(Container target) {
            if (delegate instanceof LayoutManager2 layoutManager) {
                return layoutManager.getLayoutAlignmentY(target);
            }
            return 0.5f;
        }

        @Override
        public void invalidateLayout(Container target) {
            if (delegate instanceof LayoutManager2 layoutManager) {
                layoutManager.invalidateLayout(target);
            }
        }

        @Override
        public void layoutContainer(Container parent) {
            Dimension actualSize = parent.getSize();
            double scale = Math.min(
                    (double) actualSize.width / designSize.width,
                    (double) actualSize.height / designSize.height);
            if (scale <= 0 || Double.isInfinite(scale) || Double.isNaN(scale)) {
                delegate.layoutContainer(parent);
                return;
            }

            for (Map.Entry<Component, Font> entry : originalFonts.entrySet()) {
                entry.getKey().setFont(entry.getValue());
            }

            parent.setSize(designSize);
            delegate.layoutContainer(parent);

            int offsetX = (int) Math.round((actualSize.width - designSize.width * scale) / 2);
            int offsetY = (int) Math.round((actualSize.height - designSize.height * scale) / 2);
            for (Component component : parent.getComponents()) {
                Rectangle bounds = component.getBounds();
                component.setBounds(
                        offsetX + (int) Math.round(bounds.x * scale),
                        offsetY + (int) Math.round(bounds.y * scale),
                        (int) Math.round(bounds.width * scale),
                        (int) Math.round(bounds.height * scale));
            }
            parent.setSize(actualSize);

            float fontScale = (float) Math.min(scale, MAX_FONT_SCALE);
            for (Map.Entry<Component, Font> entry : originalFonts.entrySet()) {
                Font originalFont = entry.getValue();
                entry.getKey().setFont(originalFont.deriveFont(
                        Math.max(1f, originalFont.getSize2D() * fontScale)));
                if (entry.getKey() instanceof JTable table) {
                    int rowHeight = originalRowHeights.getOrDefault(table, table.getRowHeight());
                    table.setRowHeight(Math.max(1, Math.round(rowHeight * fontScale)));
                }
            }
        }
    }
}
