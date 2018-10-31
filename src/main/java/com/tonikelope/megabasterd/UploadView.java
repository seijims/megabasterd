package com.tonikelope.megabasterd;

import static com.tonikelope.megabasterd.MainPanel.*;
import static com.tonikelope.megabasterd.MiscTools.*;
import static com.tonikelope.megabasterd.Transference.*;
import java.awt.Color;
import static java.lang.Integer.MAX_VALUE;
import java.util.concurrent.Callable;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

/**
 *
 * @author tonikelope
 */
public final class UploadView extends javax.swing.JPanel implements TransferenceView {

    private final Upload _upload;

    public JLabel getSlot_status_label() {
        return slot_status_label;
    }

    public JSpinner getSlots_spinner() {
        return slots_spinner;
    }

    public JLabel getFile_name_label() {
        return file_name_label;
    }

    public JLabel getFile_size_label() {
        return file_size_label;
    }

    public JProgressBar getProgress_pbar() {
        return progress_pbar;
    }

    public JButton getRestart_button() {
        return restart_button;
    }

    public JButton getClose_button() {
        return close_button;
    }

    public JButton getPause_button() {
        return pause_button;
    }

    public JLabel getSlots_label() {
        return slots_label;
    }

    public JLabel getSpeed_label() {
        return speed_label;
    }

    public JButton getStop_button() {
        return stop_button;
    }

    public JButton getFile_link_button() {
        return file_link_button;
    }

    public JButton getFolder_link_button() {
        return folder_link_button;
    }

    public JLabel getStatus_label() {
        return status_label;
    }

    public UploadView(Upload upload) {

        initComponents();

        updateFonts(this, DEFAULT_FONT, upload.getMain_panel().getZoom_factor());

        translateLabels(this);

        _upload = upload;

        slots_spinner.setModel(new SpinnerNumberModel(_upload.getMain_panel().getDefault_slots_up(), MIN_WORKERS, MAX_WORKERS, 1));

        ((JSpinner.DefaultEditor) slots_spinner.getEditor()).getTextField().setEditable(false);

        speed_label.setForeground(new Color(0, 128, 255));

        progress_pbar.setMinimum(0);
        progress_pbar.setMaximum(MAX_VALUE);
        progress_pbar.setStringPainted(true);

        status_label.setText("");

        for (JComponent c : new JComponent[]{slots_spinner, slots_label, pause_button, stop_button, speed_label, progress_pbar, file_name_label, close_button, restart_button, file_size_label}) {

            c.setVisible(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        status_label = new javax.swing.JLabel();
        slots_label = new javax.swing.JLabel();
        slots_spinner = new javax.swing.JSpinner();
        speed_label = new javax.swing.JLabel();
        progress_pbar = new javax.swing.JProgressBar();
        pause_button = new javax.swing.JButton();
        stop_button = new javax.swing.JButton();
        file_name_label = new javax.swing.JLabel();
        close_button = new javax.swing.JButton();
        restart_button = new javax.swing.JButton();
        file_size_label = new javax.swing.JLabel();
        slot_status_label = new javax.swing.JLabel();
        folder_link_button = new javax.swing.JButton();
        file_link_button = new javax.swing.JButton();

        setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 3, true));

        status_label.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        status_label.setForeground(new java.awt.Color(102, 102, 102));
        status_label.setText("status");
        status_label.setDoubleBuffered(true);

        slots_label.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        slots_label.setText("Slots");
        slots_label.setDoubleBuffered(true);

        slots_spinner.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        slots_spinner.setToolTipText("Slots");
        slots_spinner.setDoubleBuffered(true);
        slots_spinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                slots_spinnerStateChanged(evt);
            }
        });

        speed_label.setFont(new java.awt.Font("Dialog", 1, 26)); // NOI18N
        speed_label.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        speed_label.setText("speed");
        speed_label.setDoubleBuffered(true);

        progress_pbar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        progress_pbar.setDoubleBuffered(true);

        pause_button.setBackground(new java.awt.Color(255, 153, 0));
        pause_button.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        pause_button.setForeground(java.awt.Color.white);
        pause_button.setText("PAUSE UPLOAD");
        pause_button.setDoubleBuffered(true);
        pause_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pause_buttonActionPerformed(evt);
            }
        });

        stop_button.setBackground(new java.awt.Color(255, 0, 0));
        stop_button.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        stop_button.setForeground(java.awt.Color.white);
        stop_button.setText("CANCEL UPLOAD");
        stop_button.setDoubleBuffered(true);
        stop_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stop_buttonActionPerformed(evt);
            }
        });

        file_name_label.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        file_name_label.setForeground(new java.awt.Color(0, 102, 153));
        file_name_label.setText("Archivo de prueba xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        file_name_label.setDoubleBuffered(true);

        close_button.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        close_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-cancel-30.png"))); // NOI18N
        close_button.setText("Close");
        close_button.setDoubleBuffered(true);
        close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_buttonActionPerformed(evt);
            }
        });

        restart_button.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        restart_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-restart-30.png"))); // NOI18N
        restart_button.setText("Restart");
        restart_button.setDoubleBuffered(true);
        restart_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restart_buttonActionPerformed(evt);
            }
        });

        file_size_label.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        file_size_label.setForeground(new java.awt.Color(0, 102, 153));
        file_size_label.setText("500 MB");
        file_size_label.setDoubleBuffered(true);

        slot_status_label.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        slot_status_label.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        slot_status_label.setDoubleBuffered(true);

        folder_link_button.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        folder_link_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-copy-to-clipboard-30.png"))); // NOI18N
        folder_link_button.setText("Copy folder link");
        folder_link_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folder_link_buttonActionPerformed(evt);
            }
        });

        file_link_button.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        file_link_button.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons8-copy-to-clipboard-30.png"))); // NOI18N
        file_link_button.setText("Copy file link");
        file_link_button.setEnabled(false);
        file_link_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                file_link_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progress_pbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(file_size_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6)
                        .addComponent(folder_link_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(file_link_button))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(file_name_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slot_status_label))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(status_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slots_label)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(slots_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(speed_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pause_button))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(close_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restart_button)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(stop_button)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slots_spinner, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(slots_label)
                    .addComponent(status_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(slot_status_label)
                    .addComponent(file_name_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(folder_link_button)
                    .addComponent(file_link_button)
                    .addComponent(file_size_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progress_pbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(speed_label)
                    .addComponent(pause_button))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(close_button)
                        .addComponent(restart_button))
                    .addComponent(stop_button))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void slots_spinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_slots_spinnerStateChanged

        THREAD_POOL.execute(new Runnable() {

            @Override
            public void run() {

                _upload.checkSlotsAndWorkers();

            }
        });
    }//GEN-LAST:event_slots_spinnerStateChanged

    private void close_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_buttonActionPerformed

        _upload.close();

    }//GEN-LAST:event_close_buttonActionPerformed

    private void restart_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restart_buttonActionPerformed

        _upload.restart();

    }//GEN-LAST:event_restart_buttonActionPerformed

    private void stop_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stop_buttonActionPerformed

        _upload.stop();

    }//GEN-LAST:event_stop_buttonActionPerformed

    private void pause_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pause_buttonActionPerformed

        _upload.pause();

    }//GEN-LAST:event_pause_buttonActionPerformed

    public void hideAllExceptStatus() {

        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                for (JComponent c : new JComponent[]{speed_label, slots_spinner, slots_label, slot_status_label, pause_button, stop_button, progress_pbar}) {

                    c.setVisible(false);
                }

                for (JComponent c : new JComponent[]{status_label, file_name_label, file_size_label}) {

                    c.setVisible(true);
                }
            }
        });
    }

    private void folder_link_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folder_link_buttonActionPerformed

        folder_link_button.setEnabled(false);

        folder_link_button.setText(LabelTranslatorSingleton.getInstance().translate("Please wait..."));

        copyTextToClipboard(_upload.getFolder_link());

        folder_link_button.setText(LabelTranslatorSingleton.getInstance().translate("Copy folder link"));

        JOptionPane.showMessageDialog(_upload.getMain_panel().getView(), LabelTranslatorSingleton.getInstance().translate("MEGA folder link was copied to clipboard!"));

        folder_link_button.setEnabled(true);
    }//GEN-LAST:event_folder_link_buttonActionPerformed

    private void file_link_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_file_link_buttonActionPerformed

        file_link_button.setEnabled(false);

        file_link_button.setText(LabelTranslatorSingleton.getInstance().translate("Please wait..."));

        copyTextToClipboard(_upload.getFile_link());

        file_link_button.setText(LabelTranslatorSingleton.getInstance().translate("Copy file link"));

        JOptionPane.showMessageDialog(_upload.getMain_panel().getView(), LabelTranslatorSingleton.getInstance().translate("MEGA file link was copied to clipboard!"));

        file_link_button.setEnabled(true);

    }//GEN-LAST:event_file_link_buttonActionPerformed

    @Override
    public void pause() {

        printStatusNormal("Pausing upload ...");

        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                for (JComponent c : new JComponent[]{pause_button, speed_label, slots_label, slots_spinner, progress_pbar, file_name_label, file_size_label}) {

                    c.setEnabled(false);
                }

                for (JComponent c : new JComponent[]{stop_button}) {

                    c.setVisible(true);
                }
            }
        });
    }

    @Override
    public void stop(String status) {

        printStatusNormal(status);

        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                for (JComponent c : new JComponent[]{pause_button, stop_button, speed_label, slots_label, slots_spinner, progress_pbar, file_name_label, file_size_label}) {

                    c.setEnabled(false);
                }

            }
        });
    }

    @Override
    public void resume() {

        printStatusNormal("Uploading file to mega ...");

        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                for (JComponent c : new JComponent[]{pause_button, speed_label, slots_label, slots_spinner, progress_pbar, file_name_label, file_size_label}) {

                    c.setEnabled(true);
                }

                for (JComponent c : new JComponent[]{stop_button}) {

                    c.setVisible(false);
                }

                pause_button.setText(LabelTranslatorSingleton.getInstance().translate("PAUSE UPLOAD"));
                _upload.getMain_panel().getView().getPause_all_up_button().setVisible(true);
            }
        });
    }

    @Override
    public void updateSpeed(String sp, Boolean visible) {

        if (sp != null) {

            speed_label.setText(sp);
        }

        if (visible != null) {

            speed_label.setVisible(visible);
        }
    }

    @Override
    public void updateProgressBar(final long progress, final double bar_rate) {

        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                progress_pbar.setValue((int) Math.floor(bar_rate * progress));
            }
        });
    }

    @Override
    public void updateProgressBar(final int value) {
        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                progress_pbar.setValue(value);
            }
        });
    }

    @Override
    public void printStatusError(final String message) {

        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                status_label.setForeground(Color.red);
                status_label.setText(LabelTranslatorSingleton.getInstance().translate(message));
            }
        });
    }

    @Override
    public void printStatusOK(final String message) {

        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                status_label.setForeground(new Color(0, 170, 0));
                status_label.setText(LabelTranslatorSingleton.getInstance().translate(message));
            }
        });
    }

    @Override
    public void printStatusNormal(final String message) {

        swingInvoke(
                new Runnable() {
            @Override
            public void run() {

                status_label.setForeground(new Color(102, 102, 102));
                status_label.setText(LabelTranslatorSingleton.getInstance().translate(message));
            }
        });

    }

    @Override
    public void updateSlotsStatus() {

        synchronized (_upload.getWorkers_lock()) {

            int conta_exit = 0;

            for (ChunkUploader c : _upload.getChunkworkers()) {

                if (c.isExit()) {

                    conta_exit++;
                }
            }

            int conta_error = 0;

            for (ChunkUploader c : _upload.getChunkworkers()) {

                if (c.isError_wait()) {

                    conta_error++;
                }
            }

            if (conta_error > 0) {

                slot_status_label.setForeground(Color.red);

            } else {

                slot_status_label.setForeground(Color.black);
            }

            slot_status_label.setText((conta_exit > 0 ? "Removing: " + conta_exit : "") + (conta_error > 0 ? ((conta_exit > 0 ? " / " : "") + "Error: " + conta_error) : ""));

        }
    }

    @Override
    public int getSlots() {
        return (int) swingInvokeAndWaitForReturn(new Callable() {

            @Override
            public Object call() throws Exception {

                return getSlots_spinner().getValue();
            }

        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close_button;
    private javax.swing.JButton file_link_button;
    private javax.swing.JLabel file_name_label;
    private javax.swing.JLabel file_size_label;
    private javax.swing.JButton folder_link_button;
    private javax.swing.JButton pause_button;
    private javax.swing.JProgressBar progress_pbar;
    private javax.swing.JButton restart_button;
    private javax.swing.JLabel slot_status_label;
    private javax.swing.JLabel slots_label;
    private javax.swing.JSpinner slots_spinner;
    private javax.swing.JLabel speed_label;
    private javax.swing.JLabel status_label;
    private javax.swing.JButton stop_button;
    // End of variables declaration//GEN-END:variables

}
