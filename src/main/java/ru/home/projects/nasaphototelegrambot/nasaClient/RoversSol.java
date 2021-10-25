package ru.home.projects.nasaphototelegrambot.nasaClient;

public enum RoversSol {

    CURIOSITY_ROVER_MIN_SOL (1),
    CURIOSITY_ROVER_MAX_SOL (3276),
    OPPORTUNITY_ROVER_MIN_SOL (1),
    OPPORTUNITY_ROVER_MAX_SOL (5111),
    SPIRIT_ROVER_MIN_SOL (1),
    SPIRIT_ROVER_MAX_SOL (2208);

    private final int roversSol;

    RoversSol(int roversSol) {
        this.roversSol = roversSol;
    }

    public int getRoversSol() {
        return roversSol;
    }
}
