/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This is a demo program showing the use of the CANSparkMax class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  private XboxController m_stick;
  private static final int intakeID = 25;
  private static final int magazineID = 26;
  private static final int shooterID = 27;
  private static final int climberLID = 33;
  private static final int climberRID = 31;
  private CANSparkMax m_intake;
  private CANSparkMax m_magazine;
  private CANSparkMax m_climberL;
  private CANSparkMax m_climberR;
  private TalonFX m_shooter;
  private PowerDistribution PDH;
  // private Servo latcherL;
  // private Servo latcherR;

  @Override
  public void robotInit() {
    
    PDH = new PowerDistribution(1, ModuleType.kRev);
    PDH.clearStickyFaults();

    m_intake = new CANSparkMax(intakeID, MotorType.kBrushed);
    m_intake.restoreFactoryDefaults();
    m_magazine = new CANSparkMax(magazineID, MotorType.kBrushless);
    m_magazine.restoreFactoryDefaults();

    m_climberL = new CANSparkMax(climberLID, MotorType.kBrushless);
    m_climberL.restoreFactoryDefaults();
    m_climberR = new CANSparkMax(climberRID, MotorType.kBrushless);
    m_climberR.restoreFactoryDefaults();

    m_shooter = new TalonFX(shooterID);
    m_shooter.configFactoryDefault();

    // latcherL = new Servo(0);
    // latcherR = new Servo(0); 

    m_intake.setIdleMode(IdleMode.kCoast);
    m_magazine.setIdleMode(IdleMode.kCoast);
    m_climberL.setIdleMode(IdleMode.kBrake);
    m_climberR.setIdleMode(IdleMode.kBrake);

    m_stick = new XboxController(0);

    SmartDashboard.putNumber("intake", 0.25);
    SmartDashboard.putNumber("magazine", 0.25);
    SmartDashboard.putNumber("shooter", 0.25);
    SmartDashboard.putNumber("climberL", 0.25);
    SmartDashboard.putNumber("climberR", 0.25);


  }

  @Override
  public void teleopPeriodic() {
    double intake = SmartDashboard.getNumber("intake", 0.25);
    double magazine = SmartDashboard.getNumber("magazine", 0.25);
    double shooter = SmartDashboard.getNumber("shooter", 0.25);
    double climberL = SmartDashboard.getNumber("climberL", 0.25);
    double climberR = SmartDashboard.getNumber("climberR", 0.25);

    if(m_stick.getAButton()) {
      m_intake.set(intake);
    }
    else {
      m_intake.set(0);
    }
    if(m_stick.getBButton()) {
      m_magazine.set(magazine);
    }
    else {
      m_magazine.set(0);
    }
    if(m_stick.getBButton()) {
      m_magazine.set(magazine);
    }
    else {
      m_magazine.set(0);
    }

    if(m_stick.getLeftBumper()){
      m_climberL.set(climberL);
    } else {
      m_climberL.set(0);
    }

    if(m_stick.getRightBumper()){
      m_climberR.set(climberR);
    } else {
      m_climberR.set(0);
    }

    if(m_stick.getXButton()) {
      m_shooter.set(ControlMode.PercentOutput, shooter);
    }
    else {
      m_shooter.set(ControlMode.PercentOutput, 0.0);
    }
  }
}
