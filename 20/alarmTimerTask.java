package bai20_alarm;

import java.util.TimerTask;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;

class alarmTimerTask extends TimerTask {
    /**
         * 
         */
        private AlarmMIDlet alarmMIDlet;

        /**
         * @param alarmMIDlet
         */
        alarmTimerTask(AlarmMIDlet alarmMIDlet) {
                this.alarmMIDlet = alarmMIDlet;
        }

        public final void run() {
      Alert aAlert = new Alert("Alert!");
      aAlert.setTimeout(Alert.FOREVER);
      aAlert.setType(AlertType.ALARM);
      AlertType.ERROR.playSound(this.alarmMIDlet.display);
      this.alarmMIDlet.display.setCurrent(aAlert);
      cancel();
    }
  }
