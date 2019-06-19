package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.*;
import edu.wpi.first.wpilibj.command.Subsystem;

import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class Clock extends Subsystem {

  public TalonSRX clock = new TalonSRX(10);
  
  // Create preset positions with a name and a corresponding encoder value
  // These preset names are used in OI
  public static enum ClockPreset{
    TWELVE(0),
    THREE(1000),
    SIX(2000),
    NINE(3000);

    public double clockPosition;
    private ClockPreset(double clockPosition) {
        this.clockPosition = clockPosition;
      }
    }

  // Configures PID values, sets velocity & acceleration, determines in what direction is positive for the encoders
  public void configMotorController(int timeout){
    clock.config_kP(0, 1, timeout);
    clock.config_kI(0, 0, timeout);
    clock.config_kD(0, 0, timeout);
    clock.config_kF(0, 0, timeout);
    //clock.config_IntegralZone(0, 0, timeout);
  
    clock.configMotionAcceleration(200, timeout);
    clock.configMotionCruiseVelocity(800, timeout); // 100 ticks per 100 ms or 1000 ticks per second
    clock.setNeutralMode(NeutralMode.Brake);
  
    clock.setSensorPhase(false);
    clock.setInverted(false);
  
    clock.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, timeout);
  }  

  // Needed to call the configMotorController method above
  public Clock (){
    configMotorController(10);
  }

  // Set the default command for a subsystem here. 
  @Override
  public void initDefaultCommand() {  
    setDefaultCommand(new SetClock(OI.clockManual));
  }

  // Able to return values and read them off of Shuffleboard or the SmartDashboard
  public void periodic(){
    SmartDashboard.putNumber("Current Clock Position", getCurrentPosition());
    //SmartDashboard.putNumber("Desired Clock Position", clock.getClosedLoopTarget());
  }

  // Method that will return the clock's current position
  public int getCurrentPosition() {
    return clock.getSelectedSensorPosition(0);
  }

  // public double getDesiredPosition(){
  //   return clock.getClosedLoopTarget();
  // }

  // Method that will set the position of the clock to a given position
  public void setPosition(ClockPreset position){
    clock.set(ControlMode.MotionMagic, position.clockPosition);
  }

  // Method that will set the speed of the clock motor.
  // Speed ranges from 1 to -1 where 1 is 100% speed in one direction while -1 is 100% speed in the opposite direction
  public void setSpeed(double speed){
    clock.set(ControlMode.PercentOutput, speed);
  }

  // Method that will stop the clock motor when called
  public void stop() {
    clock.set(ControlMode.PercentOutput, 0);
  }

}