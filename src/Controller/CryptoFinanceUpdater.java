package Controller;

import Model.IModelCountainer;
import Model.ModelFactory;
import View.JFrame_Main;


public class CryptoFinanceUpdater implements IControllerApp{
    static private CryptoFinanceUpdater mObjController;  
    static private IModelCountainer mObjModelCountainer;  
    private JFrame_Main mFrmMain;

	@Override
	public void notify(AppCommandMessage msg) {
		/* Ideally This should be a 2-phase process, notify should place in some sort of list, 
		 * and then another thread should parse and process the msg.execute();
		 * But for sake of simplicity, this will be a direct execute for now. 
		 */ 
		msg.execute(this, mObjModelCountainer, mFrmMain);
	}

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                        if ("Nimbus".equals(info.getName())) {
                                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                                break;
                        }
                }
        } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(JFrame_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(JFrame_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(JFrame_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(JFrame_Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            mObjController= new CryptoFinanceUpdater();
            mObjModelCountainer= ModelFactory.getModelCountainer();

            mObjController.mFrmMain = new JFrame_Main();
			mObjController.mFrmMain.setController(mObjController);
            mObjController.mFrmMain.setVisible(true);
        });
    }
}
