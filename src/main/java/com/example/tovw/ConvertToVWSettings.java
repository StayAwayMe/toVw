package com.example.tovw;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(
        name = "ConvertToVWSettings",
        storages = @Storage("toVWSettings.xml")
)
public class ConvertToVWSettings implements PersistentStateComponent<ConvertToVWSettings.State> {

    public static class State {
        public double designWidth = 375.0;
    }

    private State myState = new State();

    public static ConvertToVWSettings getInstance(Project project) {
        return project.getService(ConvertToVWSettings.class);
    }

    @Nullable
    @Override
    public State getState() {
        return myState;
    }

    @Override
    public void loadState(@NotNull State state) {
        myState = state;
    }

    public double getDesignWidth() {
        return myState.designWidth;
    }

    public void setDesignWidth(double width) {
        myState.designWidth = width;
    }
}
