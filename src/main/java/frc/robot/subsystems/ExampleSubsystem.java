// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

//hi im victor dang welcome to my program
//im so so sorry for what you're about to see
//in my defense i had like six thousand tests/quizzes this thursday and friday plus rep parts and oracle production on top of that
//at the very most, one percent of this accursed thing functions the way it is intended to
//hi kevin


public class ExampleSubsystem extends SubsystemBase {

  public ExampleSubsystem() {
    motor1.restoreFactoryDefaults();
    motor2.restoreFactoryDefaults();
    motor1.setIdleMode(IdleMode.kCoast);
    motor2.setIdleMode(IdleMode.kCoast);
  }

  //@Override
  boolean runflywheel = false;
  boolean sendBallIn = false;
  int ballCounter = 0;


  
  static int IRthreshold = 0; //this should be changed to whatever the heck the threshold is for detection
  static int ballMax = 3; //this should be changed to whatever the heck the max amount of balls holdable within internals is
  static int redColor = 10; //should be changed to whatever the heck the output for the color red is
  static int blueColor = 20; //should be changed to whatever the heck the output for the color blue is
  static int normalColor = 0; //should be changed to whatever the heck the default color sensor output is


  //method for sending a ball into the intake
  public void sendBallIn(){
    sendBallIn = true;
    System.out.println("sending blal in!!!!");
    intakeTimer.reset();
    intakeTimer.start();
    motor1.set(0.5);
    motor2.set(0.5);
    //this is probably really cursed but i can't be bothered to change it and also cursed is funny
    while(intakeTimer.get() < 0.5){};
    motor1.stopMotor();
    motor2.stopMotor();
    sendBallIn = false;
  }

  public void periodic() {
    // This method will be called once per scheduler run
    System.out.println("periodic is running");

    //sees if a ball is being inserted into the internals
    if(IR1.get() > IRthreshold){
      System.out.println("you are trying to insert a ball!!!");
      if(ballCounter + 1 >= ballMax){
        //checks to see if there is space for a ball
        System.out.println("There is no space for that ballL!!!!!");
      } else{
        //send the ball in
        //run motor1 and motor2 until color sensor detects a shift
        if(sendBallIn = false){
          sendBallIn();
        }

        //checks the colors of the balls within the internals
        if(colorSensor.getColor() == /* whatever the heck the blue color object is*/ ){
          System.out.println("I see blue");
        } else if (colorSensor.getColor() == /*whatever the heck the red color object is */){
          System.out.println("I see red");
        }

        //stops the "intake"
        motor1.stopMotor();
        motor2.stopMotor();
        ballCounter+=1;
      }

    }


    //these two if statements launch balls when the A button is pressed 
    //or at least that's the intention
    //look i didn't test this code because i was busy doing rep part
    //is that my fault? yes
    if(controller.getAButtonPressed()){
      System.out.println("BUTTON PRESSED!!!");
      flywheelTimer.reset();
      flywheelTimer.start();
      runflywheel = true;
      ballCounter-=1;
    }
    if(runflywheel = true){
      if(flywheelTimer.get() < 1){
        //runs the flywheel and sends the balls within the internals up into the flywheel
        flywheel.set(0.25);
        motor2.set(1.0);
      } else {
        //stops the motors after some time so nothing else is sent into the flywheel
        flywheel.set(0);
        motor2.set(0);
        runflywheel = false;
      }
    }
  }



  //initializes the timers
  private final Timer intakeTimer = new Timer();
  private final Timer flywheelTimer = new Timer();

  //motor1 is the lower roller, motor2 is the upper roller - TALON SRXs HOW DO I IMPORT TALONSRXLIBRARY!?!?!??
  private final TalonSRX motor1 = new TalonSRX(13);
  private final TalonSRX motor2 = new TalonSRX(14);
  //private final CANSparkMax motor1 = new CANSparkMax(13, MotorType.kBrushless);
  //private final CANSparkMax motor2 = new CANSparkMax(14, MotorType.kBrushless);

  //flywheel is the flywheel
  private final CANSparkMax flywheel = new CANSparkMax(15, MotorType.kBrushless);

  //IR1 is the first IR sensor, closest to where balls are inserted
  private final AnalogPotentiometer IR1 = new AnalogPotentiometer(0);
  //IR2 is the second, higher up IR sensor
  private final AnalogPotentiometer IR2 = new AnalogPotentiometer(1);

  //colorSensor is the color sensor what a surprise
  private final ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kMXP);//i have no idea why this is an error

  //initializes the Xbox controller(or at least I think it does)
  private final XboxController controller = new XboxController(0);


  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
