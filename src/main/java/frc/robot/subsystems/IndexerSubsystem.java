package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel;
import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class IndexerSubsystem extends SubsystemBase {
    private final SparkMax loader = new SparkMax(Constants.IndexerConstants.LoaderID, SparkLowLevel.MotorType.kBrushless);

    public void louderUp() {
        loader.setVoltage(Constants.IndexerConstants.LoaderVoltage);
    }

    public void louderDown() {
        loader.setVoltage(Constants.IndexerConstants.LoaderVoltageReverse);
    }

    public void louderStop() {
        loader.setVoltage(0);
    }



}

