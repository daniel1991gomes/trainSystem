package mmt.app.main;

import java.io.FileNotFoundException;
import java.io.IOException;

import mmt.core.TicketOffice;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;

//FIXME import other classes if necessary

/**
 * §3.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<TicketOffice> {
  
  Input<String> _f;

  /**
   * @param receiver
   */
  public DoSave(TicketOffice receiver) {
    super(Label.SAVE, receiver);
    _f = _form.addStringInput(mmt.app.main.Message.newSaveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();
    try{
      _receiver.save(_f.value());
    } catch(FileNotFoundException fnfe){

    }catch(IOException ioe){

    }
  }
}
