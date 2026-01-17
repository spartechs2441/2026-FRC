package frc.robot.subsystems;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.EncoderConfig;
import com.revrobotics.spark.config.EncoderConfigAccessor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import java.beans.Encoder;

public class ShooterSubsystem extends SubsystemBase {
    private final SparkMax shooterMotorController = new SparkMax(Constants.TemperoryID, SparkLowLevel.MotorType.kBrushless);
    private final AbsoluteEncoder shooterEncoder;

    public ShooterSubsystem () {
        this.shooterEncoder = shooterMotorController.getAbsoluteEncoder();
    }



    public void flywheelIn() {
        shooterMotorController.setVoltage(Constants.TemperoryVoltage);
    }

    public void flywheelOut() {
        shooterMotorController.setVoltage(-Constants.TemperoryVoltage);
    }

    public void flywheelStop() {
        shooterMotorController.setVoltage(0);
    }
}
