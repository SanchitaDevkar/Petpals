package dao;

import java.util.ArrayList;
import java.util.List;

import entity.PetShelter;

public class AdoptionEvent {
    private List<IAdoptable> participants;

    public AdoptionEvent() {
        participants = new ArrayList<>();
    }

    public void registerParticipant(PetShelter shelter) {
        participants.add((IAdoptable) shelter);
        System.out.println("Participant registered for the adoption event.");
    }

    public void hostEvent() {
        System.out.println("Hosting Adoption Event...");
        for (IAdoptable participant : participants) {
            try {
                participant.adopt();
            } catch (Exception e) {
                System.out.println("Error during adoption: " + e.getMessage());
            }
        }
        System.out.println("Adoption Event concluded.");
    }

    public List<IAdoptable> getParticipants() {
        return participants;
    }
}
