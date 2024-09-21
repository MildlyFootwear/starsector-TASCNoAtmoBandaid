package Shoey.tascNoAtmo;

import com.fs.starfarer.api.Global;
import com.fs.starfarer.api.campaign.*;
import com.fs.starfarer.api.campaign.econ.MarketAPI;
import com.fs.starfarer.api.characters.AbilityPlugin;
import com.fs.starfarer.api.characters.PersonAPI;
import com.fs.starfarer.api.combat.EngagementResultAPI;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.List;

public class StationAtmoListener implements CampaignEventListener {

    private Logger log = Global.getLogger(this.getClass());

    void clean()
    {
        log.setLevel(Level.INFO);

        for (LocationAPI location : Global.getSector().getAllLocations())
        {
            List<MarketAPI> markets = Global.getSector().getEconomy().getMarkets(location);
            if (markets == null || markets.size() == 0)
                continue;
            for (MarketAPI market : markets)
            {

                if (market.hasCondition("no_atmosphere")) {
                    String mID = market.getId();
                    if (mID.contains("boggled"))
                        log.info("Cleaning " + market.getName() + " because boggled station");
                    else if (market.getTags().contains("aotd_hypershunt"))
                        log.info("Cleaning " + market.getName() + " because hypershunt");
                    else if (market.getPlanetEntity() == null)
                        log.info("Cleaning " + market.getName() + " because no planet entity");
                    else {
                        log.info(market.getName()+" not a station");
                        continue;
                    }
                    market.removeCondition("no_atmosphere");
                    log.info("Cleaned " + market.getName() + " " + mID);
                }

            }
        }
    }

    @Override
    public void reportPlayerOpenedMarket(MarketAPI market) {

    }

    @Override
    public void reportPlayerClosedMarket(MarketAPI market) {

    }

    @Override
    public void reportPlayerOpenedMarketAndCargoUpdated(MarketAPI market) {

    }

    @Override
    public void reportEncounterLootGenerated(FleetEncounterContextPlugin plugin, CargoAPI loot) {

    }

    @Override
    public void reportPlayerMarketTransaction(PlayerMarketTransaction transaction) {

    }

    @Override
    public void reportBattleOccurred(CampaignFleetAPI primaryWinner, BattleAPI battle) {

    }

    @Override
    public void reportBattleFinished(CampaignFleetAPI primaryWinner, BattleAPI battle) {

    }

    @Override
    public void reportPlayerEngagement(EngagementResultAPI result) {

    }

    @Override
    public void reportFleetDespawned(CampaignFleetAPI fleet, FleetDespawnReason reason, Object param) {

    }

    @Override
    public void reportFleetSpawned(CampaignFleetAPI fleet) {

    }

    @Override
    public void reportFleetReachedEntity(CampaignFleetAPI fleet, SectorEntityToken entity) {

    }

    @Override
    public void reportFleetJumped(CampaignFleetAPI fleet, SectorEntityToken from, JumpPointAPI.JumpDestination to) {

    }

    @Override
    public void reportShownInteractionDialog(InteractionDialogAPI dialog) {

    }

    @Override
    public void reportPlayerReputationChange(String faction, float delta) {

    }

    @Override
    public void reportPlayerReputationChange(PersonAPI person, float delta) {

    }

    @Override
    public void reportPlayerActivatedAbility(AbilityPlugin ability, Object param) {

    }

    @Override
    public void reportPlayerDeactivatedAbility(AbilityPlugin ability, Object param) {

    }

    @Override
    public void reportPlayerDumpedCargo(CargoAPI cargo) {

    }

    @Override
    public void reportPlayerDidNotTakeCargo(CargoAPI cargo) {

    }

    @Override
    public void reportEconomyTick(int iterIndex) {

    }

    @Override
    public void reportEconomyMonthEnd() {

        clean();

    }
}
