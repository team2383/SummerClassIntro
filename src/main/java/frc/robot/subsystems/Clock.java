package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.RobotMap;

public class Clock extends Subsystem {

  public TalonSRX clock = new TalonSRX(RobotMap.clockPort);
  
  // Create preset positions with a name and a corresponding encoder value
  // These preset names are used in OI
  public static enum ClockPreset{
    TWELVE(0),
    THREE(20),
    SIX(40),
    NINE(60);

    public double clockPosition;
    private ClockPreset(double clockPosition) {
        this.clockPosition = clockPosition;
      }
    }

  // Configures PID values, sets velocity & acceleration, determines in what direction is positive for the encoders
  public void configMotorController(int timeout){
    clock.config_kP(0, 4, timeout);
    clock.config_kI(0, 0, timeout);
    clock.config_kD(0, 55, timeout);
    clock.config_kF(0, 4.55, timeout);
    clock.config_IntegralZone(0, 0, timeout);
  
    clock.configMotionAcceleration(300, timeout); //was 400
    clock.configMotionCruiseVelocity(200, timeout); //was 400
    clock.setNeutralMode(NeutralMode.Brake);
  
    clock.setSensorPhase(true);
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
  }

  // Able to return values and read them off of Shuffleboard or the SmartDashboard
  public void periodic(){
    SmartDashboard.putNumber("Clock pos", getCurrentPosition());
  }

  // Method that will return the clock's current position
  public int getCurrentPosition() {
    return clock.getSelectedSensorPosition(0);
  }

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