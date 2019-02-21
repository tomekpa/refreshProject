//package spring.test.LoopTriggerOnStart;
//
//import com.ab.ah.scad.acl.error.ExecuteException;
//import com.wba.rxfulfillment.documentcreation.leaflet.service.handler.HandleGenerateLeafletEvent;
//import com.wba.rxfulfillmentdocumentcreation.leaflet.to.GenerateRxLeafletTO;
//
//public class LeafletTrigerRunnable implements Runnable {
//
//  HandleGenerateLeafletEvent handleGenerateLeafletEvent;
//
//  LeafletTrigerRunnable(HandleGenerateLeafletEvent handleGenerateLeafletEvent) {
//    this.handleGenerateLeafletEvent = handleGenerateLeafletEvent;
//  }
//
//  Boolean triggerMe = false;
//
//  @Override
//  public void run() {
//    System.out.println("INIT");
//    while (true) {
//      System.out.println("INSIDE LOOP");
//      if (triggerMe) {
//        System.out.println("YOUPEEEEEEEEEEEEEEEEEEEEEE");
//
//        GenerateRxLeafletTO generateRxLeafletTO = new GenerateRxLeafletTO();
//        try {
//          handleGenerateLeafletEvent.handleGenerateLeaflet(generateRxLeafletTO);
//        } catch (ExecuteException e) {
//          e.printStackTrace();
//        }
//
//        try {
//          Thread.sleep(1000);
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//        triggerMe = false;
//      }
//      try {
//        Thread.sleep(1000);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//    }
//  }
//}
