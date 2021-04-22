package generated.main;

import de.fhg.iais.roberta.runtime.*;
import de.fhg.iais.roberta.runtime.ev3.*;

import de.fhg.iais.roberta.mode.general.*;
import de.fhg.iais.roberta.mode.action.*;
import de.fhg.iais.roberta.mode.sensor.*;
import de.fhg.iais.roberta.mode.action.ev3.*;
import de.fhg.iais.roberta.mode.sensor.ev3.*;

import de.fhg.iais.roberta.components.*;

import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import lejos.remote.nxt.NXTConnection;

public class obr {
    private static Configuration brickConfiguration;
    
    private Set<UsedSensor> usedSensors = new LinkedHashSet<UsedSensor>(Arrays.asList(new UsedSensor(SensorPort.S1, SensorType.COLOR, ColorSensorMode.COLOUR), new UsedSensor(SensorPort.S4, SensorType.COLOR, ColorSensorMode.COLOUR), new UsedSensor(SensorPort.S3, SensorType.COLOR, ColorSensorMode.COLOUR), new UsedSensor(SensorPort.S2, SensorType.TOUCH, TouchSensorMode.TOUCH)));
    private Hal hal = new Hal(brickConfiguration, usedSensors);
    
    public static void main(String[] args) {
        try {
             brickConfiguration = new EV3Configuration.Builder()
                .setWheelDiameter(5.6)
                .setTrackWidth(18.0)
                .addActor(ActorPort.B, new Actor(ActorType.LARGE, true, DriveDirection.FOREWARD, MotorSide.RIGHT))
                .addActor(ActorPort.C, new Actor(ActorType.LARGE, true, DriveDirection.FOREWARD, MotorSide.LEFT))
                .addSensor(SensorPort.S1, new Sensor(SensorType.COLOR))
                .addSensor(SensorPort.S2, new Sensor(SensorType.TOUCH))
                .addSensor(SensorPort.S3, new Sensor(SensorType.COLOR))
                .addSensor(SensorPort.S4, new Sensor(SensorType.COLOR))
                .build();
            
            new obr().run();
        } catch ( Exception e ) {
            Hal.displayExceptionWaitForKeyPress(e);
        }
    }
    
    
    public void run() throws Exception {
        while ( ! (( hal.getColorSensorColour(SensorPort.S1) == PickColor.RED ) && ( hal.getColorSensorColour(SensorPort.S4) == PickColor.GREEN )) ) {
            hal.regulatedDrive(DriveDirection.FOREWARD, 30);
        }
        hal.driveDistance(DriveDirection.FOREWARD, 30, 7);
        hal.rotateDirectionAngle(TurnDirection.RIGHT, 30, 45);
        while ( ! (hal.getColorSensorColour(SensorPort.S3) == PickColor.GREEN) ) {
            if ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.BLACK ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) {
                hal.regulatedDrive(DriveDirection.FOREWARD, 30);
            } else if ( hal.getColorSensorColour(SensorPort.S4) == PickColor.NONE ) {
                hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 6);
            } else {
                if ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) {
                    hal.rotateDirectionAngle(TurnDirection.LEFT, 15, 6);
                }
            }
        }
        hal.driveDistance(DriveDirection.FOREWARD, 30, 7);
        hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 45);
        while ( ! hal.isPressed(SensorPort.S2) ) {
            if ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.BLACK ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) {
                hal.regulatedDrive(DriveDirection.FOREWARD, 30);
            } else if ( ( hal.getColorSensorColour(SensorPort.S4) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S4) == PickColor.BLACK ) ) {
                hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 6);
            } else if ( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.WHITE ) && ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.WHITE ) && ( hal.getColorSensorColour(SensorPort.S4) == PickColor.WHITE ) ) ) {
                while ( ! (( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) || ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE )) ) {
                    hal.rotateDirectionAngle(TurnDirection.RIGHT, 10, 10);
                }
            } else {
                if ( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S1) == PickColor.BLACK ) ) {
                    hal.rotateDirectionAngle(TurnDirection.LEFT, 15, 9);
                }
            }
        }
        hal.driveDistance(DriveDirection.BACKWARD, 30, 5);
        hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 90);
        hal.driveDistance(DriveDirection.FOREWARD, 30, 15);
        hal.rotateDirectionAngle(TurnDirection.LEFT, 15, 90);
        hal.driveDistance(DriveDirection.FOREWARD, 30, 30);
        hal.rotateDirectionAngle(TurnDirection.LEFT, 15, 90);
        while ( ! (( ( hal.getColorSensorColour(SensorPort.S4) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S4) == PickColor.BLACK ) ) && ( ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.BLACK ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) && ( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S1) == PickColor.BLACK ) ) )) ) {
            hal.regulatedDrive(DriveDirection.FOREWARD, 30);
        }
        hal.driveDistance(DriveDirection.FOREWARD, 30, 2);
        hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 90);
        while ( ! (hal.getColorSensorColour(SensorPort.S1) == PickColor.GREEN) ) {
            if ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.BLACK ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) {
                hal.regulatedDrive(DriveDirection.FOREWARD, 30);
            } else if ( ( hal.getColorSensorColour(SensorPort.S4) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S4) == PickColor.BLACK ) ) {
                hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 6);
            } else {
                if ( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S1) == PickColor.BLACK ) ) {
                    hal.rotateDirectionAngle(TurnDirection.LEFT, 15, 9);
                }
            }
        }
        hal.driveDistance(DriveDirection.FOREWARD, 30, 3);
        while ( ! (hal.getColorSensorColour(SensorPort.S1) == PickColor.GREEN) ) {
            if ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.BLACK ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) {
                hal.regulatedDrive(DriveDirection.FOREWARD, 30);
            } else if ( ( hal.getColorSensorColour(SensorPort.S4) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S4) == PickColor.BLACK ) ) {
                hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 6);
            } else {
                if ( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S1) == PickColor.BLACK ) ) {
                    hal.rotateDirectionAngle(TurnDirection.LEFT, 15, 9);
                }
            }
        }
        hal.driveDistance(DriveDirection.FOREWARD, 30, 3);
        hal.rotateDirectionAngle(TurnDirection.LEFT, 10, 90);
        hal.driveDistance(DriveDirection.FOREWARD, 30, 5);
        while ( ! (hal.getColorSensorColour(SensorPort.S3) == PickColor.GREEN) ) {
            if ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.BLACK ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) {
                hal.regulatedDrive(DriveDirection.FOREWARD, 30);
            } else if ( ( hal.getColorSensorColour(SensorPort.S4) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S4) == PickColor.BLACK ) ) {
                hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 6);
            } else if ( hal.getColorSensorColour(SensorPort.S4) == PickColor.RED ) {
                hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 6);
                hal.driveDistance(DriveDirection.FOREWARD, 15, 3);
            } else {
                if ( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S1) == PickColor.BLACK ) ) {
                    hal.rotateDirectionAngle(TurnDirection.LEFT, 15, 9);
                }
            }
        }
        hal.driveDistance(DriveDirection.FOREWARD, 30, 6);
        hal.rotateDirectionAngle(TurnDirection.LEFT, 10, 90);
        while ( ! (( hal.getColorSensorColour(SensorPort.S1) == PickColor.GREEN ) && ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.GREEN ) && ( hal.getColorSensorColour(SensorPort.S4) == PickColor.GREEN ) )) ) {
            if ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.BLACK ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) {
                hal.regulatedDrive(DriveDirection.FOREWARD, 30);
            } else if ( ( hal.getColorSensorColour(SensorPort.S4) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S4) == PickColor.BLACK ) ) {
                hal.rotateDirectionAngle(TurnDirection.RIGHT, 15, 6);
            } else if ( hal.getColorSensorColour(SensorPort.S4) == PickColor.RED ) {
                hal.driveDistance(DriveDirection.FOREWARD, 30, 6);
                hal.rotateDirectionAngle(TurnDirection.RIGHT, 30, 20);
            } else if ( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.WHITE ) && ( ( hal.getColorSensorColour(SensorPort.S3) == PickColor.WHITE ) && ( hal.getColorSensorColour(SensorPort.S4) == PickColor.WHITE ) ) ) {
                while ( ! (( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S3) == PickColor.NONE ) ) || ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE )) ) {
                    hal.rotateDirectionAngle(TurnDirection.RIGHT, 10, 10);
                }
            } else {
                if ( ( hal.getColorSensorColour(SensorPort.S1) == PickColor.NONE ) || ( hal.getColorSensorColour(SensorPort.S1) == PickColor.BLACK ) ) {
                    hal.rotateDirectionAngle(TurnDirection.LEFT, 15, 9);
                }
            }
        }
        hal.stopRegulatedDrive();
    }
}