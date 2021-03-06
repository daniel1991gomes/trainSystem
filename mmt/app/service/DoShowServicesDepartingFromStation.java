package mmt.app.service;

import mmt.core.TicketOffice;
import mmt.core.exceptions.NoSuchStationNameException;
import mmt.app.exceptions.NoSuchStationException;
import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Display;

import java.util.LinkedList;


//FIXME import other classes if necessary

/**
 * 3.2.3 Show services departing from station.
 */
public class DoShowServicesDepartingFromStation extends Command<TicketOffice> {

  private Input<String> _sName;

  /**
   * @param receiver
   */
  public DoShowServicesDepartingFromStation(TicketOffice receiver) {
    super(Label.SHOW_SERVICES_DEPARTING_FROM_STATION, receiver);
    _sName = _form.addStringInput(mmt.app.service.Message.requestStationName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();
    try{
      LinkedList<String> sList = _receiver.showServicesDepartingFrom(_sName.value());

      for(String s: sList)
        _display.addLine(s);
      _display.display();
    }catch(NoSuchStationNameException ex){
      throw new NoSuchStationException(ex.getName());
    }
  }

}
