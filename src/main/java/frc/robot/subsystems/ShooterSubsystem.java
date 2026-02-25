package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterSubsystem extends SubsystemBase {
    private final SparkMax shooterMotorController = new SparkMax(Constants.ShooterConstants.shooterMotorControllerID, SparkLowLevel.MotorType.kBrushless);

    public ShooterSubsystem () {
        RelativeEncoder shooterEncoder = shooterMotorController.getEncoder();
    }



    public void flywheelIn() {
        shooterMotorController.setVoltage(Constants.ShooterConstants.shooterMotorControllerVoltage);
    }

    public void ShooterShoot() {
        shooterMotorController.setVoltage(-Constants.ShooterConstants.shooterMotorControllerVoltageReverse);
        shooterMotorController.set(Constants.ShooterConstants.shooterSpeed);
    }

    public void ShooterStop() {
        shooterMotorController.setVoltage(0);
    }
}
