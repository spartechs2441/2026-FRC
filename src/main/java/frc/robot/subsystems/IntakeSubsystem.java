package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IntakeSubsystem extends SubsystemBase {
    private final SparkMax intakeMotorController1 = new SparkMax(Constants.TemperoryID, SparkLowLevel.MotorType.kBrushless);

    public void intakeIn() {
        intakeMotorController1.setVoltage(-Constants.TemperoryVoltage);
    }

    public void intakeOut() {
        intakeMotorController1.setVoltage(Constants.TemperoryVoltage);
    }

    public void intakeStop() {
        intakeMotorController1.setVoltage(0);
    }
}
