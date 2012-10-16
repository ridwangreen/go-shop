/**
 * File: {ID_TAG}
 * Revisions:
 *     {LOG_TAG}
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeSet;


/**
 * The Mappy program reads in city and road information from a file. It then
 * allows the user to enter commands to do things like display all cities / city
 * neighbors / roads, as well as compute and display the shortest path between
 * two cities.
 * 
 * @author sps (Sean Strout)
 * @author {Rebecca Dudley}
 */
public class Mappy {

	/**
	 * All relevant information is stored in a directed graph that stores: city
	 * name, city data, road data.
	 */
	private DiGraph<String, City, Road> graph = new ArrayDiGraph<String, City, Road>();

	/**
	 * Constructs an object by reading in the filename and creating the graph.
	 * The data in the file must be organized with city and road information as
	 * follows, one per line:<br>
	 * <br>
	 * city neighbor-city distance road<br>
	 * ...<br>
	 * 
	 * @param filename
	 *            the file name that stores the data
	 * @throws FileNotFoundException
	 *             if the file is not found
	 * @throws IOException
	 *             if there is an error reading from the file
	 */
	public Mappy(String filename) throws FileNotFoundException, IOException {
		// read map data in: source destination distance road
		Scanner input = new Scanner(new File(filename));

		while (input.hasNext()) {
			String first = input.next();
			if (!graph.isVertex(first))
				graph.addVertex(first, new City(first));
			String second = input.next();
			if (!graph.isVertex(second))
				graph.addVertex(second, new City(second));
			int distance = input.nextInt();
			String road = input.next();
			Road edge = new Road(distance, road);
			try {
				graph.addEdge(first, second, edge);
				graph.addEdge(second, first, edge);
			} catch (NoSuchVertexException nve) {
			}
		}

		input.close();
	} // Mappy

	/**
	 * Displays the list of valid commands and their descriptions.
	 */
	private void help() {
		System.out.println("help - displays this help message.");
		System.out.println("cities - displays all city information.");
		System.out.println("neighbors city - "
				+ "displays neighbors for a given city.");
		System.out.println("quit - exits the program.");
		System.out.println("roads - displays all road information.");
		System.out.println("travel source dest - "
				+ "display the shortest path between "
				+ "source and destination cities");
	} // help

	/**
	 * Displays the list of cities, sorted alphabetically using the city name
	 * using the following format:<br>
	 * <br>
	 * Map has {#} cities:<br>
	 * {First-city} has {#} road/s<br>
	 * {Second-city} has {#} road/s<br>
	 * ...<br>
	 * 
	 * @throws NoSuchVertexException
	 *             if a city does not exist in the map.
	 */
	private void cities() throws NoSuchVertexException {
		// display cities alphabetical, with # roads
		TreeSet<String> cities = new TreeSet<String>(graph.vertexKeys());
		System.out.println("Map has " + graph.numVertices() + " cities:");
		for (String c : cities) {
			System.out.println(c + " has " + graph.inDegree(c) + " road/s");
		}
	} // cities

	/**
	 * Displays the set of roads and each combined length, sorted from largest
	 * to smallest length using the following format:<br>
	 * <br>
	 * Map has {#} roads:<br>
	 * {First-road} has {#} mile/s<br>
	 * {Second-road} has {#} mile/s<br>
	 * ...<br>
	 * 
	 * @throws NoSuchVertexException
	 *             if a city does not exist in the map.
	 */
	private void roads() throws NoSuchVertexException {
		// build a map keyed by road names with total lengths as values
		final Map<String, Integer> map = new HashMap<String, Integer>();
		for (Road edge : graph.edgeData()) {
			String road = edge.getRoad();
			map.put(road, map.get(road) == null ? edge.getLength() : map
					.get(road)
					+ edge.getLength());
		}

		// sort the roads from longest to shortest
		List<String> list = new ArrayList<String>(map.keySet());
		Collections.sort(list, new Comparator<String>() {
			public int compare(String p1, String p2) {
				int result = map.get(p2) - map.get(p1);
				if (result == 0) {
					result = p1.compareTo(p2);
				}
				return result;
			}
		});

		System.out.println("Map has " + map.size() + " roads:");
		for (String road : list) {
			System.out.println(road + " has " + map.get(road) + " mile/s");
		}
	} // roads

	/**
	 * Displays information about each of the neighbors of a given city using
	 * the following format:<br>
	 * <br>
	 * {city} has {#} neighbors:<br>
	 * {First-city} is {#} mile/s on {road}<br>
	 * {Second-city} is {#} mile/s on {road}<br>
	 * ...<br>
	 * 
	 * @param city
	 *            the city to determine the neighbors for.
	 * @throws NoSuchVertexException
	 *             if a city does not exist in the map.
	 */
	private void neighbors(String city) throws NoSuchVertexException {
		// IMPLEMENT ACTIVITY 2 HERE...

		Collection<City> neighs = graph.neighborData(city);
		TreeSet<City> sNeighs = new TreeSet<City>();
		for (City c : neighs) {
			sNeighs.add(c);
		}
		System.out.println(city + " has " + sNeighs.size() + " neighbors:");
		for (City c : sNeighs) {
			System.out.println(c.getName() + " is "
					+ graph.getEdgeData(city, c.getName()).getLength()
					+ " mile/s on "
					+ graph.getEdgeData(city, c.getName()).getRoad());
		}

	} // neighbors

	/**
	 * Displays the shortest path between the source and dest cities using the
	 * following format:<br>
	 * <br>
	 * {first-city} to {second-city} via {road}, {#} miles<br>
	 * {second-city} to {third-city} via {road}, {#} miles<br>
	 * ...<br>
	 * Total distance is {#} miles<br>
	 * <br>
	 * If the source and destination are the same it should print:<br>
	 * <br>
	 * You are already there!<br>
	 * Total distance is 0 miles<br>
	 * <br>
	 * 
	 * @param source
	 *            the starting city.
	 * @param dest
	 *            the destination city.
	 * @throws NoSuchVertexException
	 *             if a city does not exist in the map.
	 */
	private void travel(String source, String dest)
			throws NoSuchVertexException {
		// IMPLEMENT ACTIVITY 3 HERE...

		if (source.equals(dest)) {
			System.out.println("You are already there!");
			System.out.println("Total distance is 0 miles");
		} else {

			Collection<City> vertices = graph.vertexData();
			graph.getVertexData(source).setCost(0);
			graph.getVertexData(source).setPredecessor(null);
			for (City c : vertices) {
				if (!c.equals(graph.getVertexData(source))) {
					c.setCost(Integer.MAX_VALUE);
					c.setPredecessor(null);
				}
			}
			do {
				City min = null;
				for (City c : vertices) {
					if (min == null) {
						min = c;
					} else if (min.compareTo(c) > 0) {
						min = c;
					}
				}
				vertices.remove(min);
				Collection<City> neighs = graph.neighborData(min.getName());
				for (City c : neighs) {
					if (vertices.contains(c)) {
						Road r = graph.getEdgeData(min.getName(), c.getName());
						if (min.getCost() + r.getLength() < c.getCost()) {
							c.setCost(min.getCost() + r.getLength());
							c.setPredecessor(min.getName());
						}
					}
				}

			} while (!vertices.isEmpty());
			int miles = graph.getVertexData(dest).getCost();
			City pred = graph.getVertexData(graph.getVertexData(dest)
					.getPredecessor());
			City cur = graph.getVertexData(dest);
			Stack<String> s = new Stack<String>();
			while (!pred.equals(graph.getVertexData(source))) {
				Road r = graph.getEdgeData(cur.getName(), pred.getName());
				s.add(pred.getName() + " to " + cur.getName()
						+ " via " + r.getRoad() + ", " + r.getLength()
						+ " miles");
				cur = graph.getVertexData(cur.getPredecessor());
				pred = graph.getVertexData(cur.getPredecessor());
			}
			Road r = graph.getEdgeData(cur.getName(), pred.getName());
			s.add(pred.getName() + " to " + cur.getName()
					+ " via " + r.getRoad() + ", " + r.getLength()
					+ " miles");
			while(!s.isEmpty()){
				System.out.println(s.pop());
			}
			System.out.println("Total distance is " + miles + " miles");
		}

	} // travel

	/**
	 * The main loop prompts and waits for user input. If the command is valid
	 * the appopriate helper function is called. If an error occurs because an
	 * invalid city name was entered, the following is displayed:<br>
	 * <br>
	 * Error, a city was not found in the map!<br>
	 * <br>
	 * Program execution continues until the 'quit' command or CTRL-D is
	 * entered.
	 */
	private void mainLoop() {
		Scanner input = new Scanner(System.in);
		String command = "";
		// run the main loop until quit or CTRL-D is entered
		do {
			System.out.print("> ");
			command = input.next();

			// invoke the appropriate function based on the user command
			try {
				if (command.equals("cities")) {
					cities();
				} else if (command.equals("help")) {
					help();
				} else if (command.equals("neighbors")) {
					neighbors(input.next());
				} else if (command.equals("roads")) {
					roads();
				} else if (command.equals("travel")) {
					travel(input.next(), input.next());
				}
			} catch (NoSuchVertexException nve) {
				System.out.println("Error, a city was not found in the map!");
			}

			input.nextLine();
		} while (!command.equals("quit"));

		input.close();
	} // mainLoop

	/**
	 * The main routine verifies the command line args, constructts the Mappy
	 * object, and executes its main loop.
	 * 
	 * @param args
	 *            the command line argument should be the name of the file the
	 *            holds the city/road information.
	 */
	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java Mappy filename");
			System.exit(0);
		}

		try {
			Mappy shortest = new Mappy(args[0]);
			shortest.mainLoop();
		} catch (FileNotFoundException fnfe) {
			System.err.println("Error opening file " + args[0]);
		} catch (IOException ioe) {
			System.err.println("Error opening file " + args[0]);
		}
	} // main
}