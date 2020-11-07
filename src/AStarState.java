import java.util.Collections;
import java.util.HashMap;

/**
 * This class stores the basic state necessary for the A* algorithm to compute a
 * path across a map.  This state includes a collection of "open waypoints" and
 * another collection of "closed waypoints."  In addition, this class provides
 * the basic operations that the A* pathfinding algorithm needs to perform its
 * processing.
 **/
public class AStarState
{
    /** This is a reference to the map that the A* algorithm is navigating. **/
    private Map2D map;

    HashMap<Location, Waypoint> OpenedWaypoint;
    HashMap<Location, Waypoint> ClosedWaypoint;

    /**
     * Initialize a new state object for the A* pathfinding algorithm to use.
     **/
    public AStarState(Map2D map)
    {
        if (map == null)
            throw new NullPointerException("map cannot be null");

        OpenedWaypoint = new HashMap<>();
        ClosedWaypoint = new HashMap<>();

        this.map = map;
    }

    /** Returns the map that the A* pathfinder is navigating. **/
    public Map2D getMap()
    {
        return map;
    }

    /**
     * This method scans through all open waypoints, and returns the waypoint
     * with the minimum total cost.  If there are no open waypoints, this method
     * returns <code>null</code>.
     **/
    public Waypoint getMinOpenWaypoint()
    {
        // TODO:  Implement.
        // DONE?

        final Waypoint[] MinOpenWaypoint = new Waypoint[1];
        final float[] j = new float[1];
        j[0] = Float.MAX_VALUE;
        OpenedWaypoint.forEach(
                (k,v) -> {
                    if (v.getTotalCost() < j[0]) {
                        MinOpenWaypoint[0] = v;
                        j[0] = v.getTotalCost();
                    }
                });

        return MinOpenWaypoint[0];//проверяет все вершины в наборе открытых вершин и возврващает ссылку на вершину с наименьшей общей стоимостью, иначе NULL
    }

    /**
     * This method adds a waypoint to (or potentially updates a waypoint already
     * in) the "open waypoints" collection.  If there is not already an open
     * waypoint at the new waypoint's location then the new waypoint is simply
     * added to the collection.  However, if there is already a waypoint at the
     * new waypoint's location, the new waypoint replaces the old one <em>only
     * if</em> the new waypoint's "previous cost" value is less than the current
     * waypoint's "previous cost" value.
     **/
    public boolean addOpenWaypoint(Waypoint newWP)
    {
        // TODO:  Implement.
        // DONE??

        if (OpenedWaypoint.get(newWP.getLocation()) == null) OpenedWaypoint.put(newWP.getLocation(), newWP);
        //если в открытых нет вершины для заданного положения
        //добавляем новую
        else if (newWP.getPreviousCost() < OpenedWaypoint.get(newWP.getLocation()).getPreviousCost())
            OpenedWaypoint.replace(newWP.getLocation(), newWP);//если есть вершина для этой локации, добавляем, если стоимость меньше чем до текущей

        return false;
    }


    /** Returns the current number of open waypoints. **/
    public int numOpenWaypoints()
    {
        // TODO:  Implement.
        // DONE???

        return OpenedWaypoint.values().size();//возвращает количество точек в наборе открытых вершин

    }


    /**
     * This method moves the waypoint at the specified location from the
     * open list to the closed list.
     **/
    public void closeWaypoint(Location loc)
    {
        // TODO:  Implement.
        // DONE??

        ClosedWaypoint.put(loc, OpenedWaypoint.get(loc));//перемещает вершину из открытых в закрытые
        OpenedWaypoint.remove(loc);

    }

    /**
     * Returns true if the collection of closed waypoints contains a waypoint
     * for the specified location.
     **/
    public boolean isLocationClosed(Location loc)
    {

        return ClosedWaypoint.containsKey(loc);//возвращает тру если указанное положение есть в наборе закрытых, иначе фолс

    }
}
