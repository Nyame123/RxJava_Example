package com.example.myrxjava.treeAndGraph.medium;

import com.example.myrxjava.ordinal.graphs.BinaryMinHeap;
import com.example.myrxjava.treeAndGraph.prep.AdjacencyListGraph;
import com.example.myrxjava.treeAndGraph.prep.Vertex;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

enum State {
    VISITING,
    UNVISITED,
    VISITED
}

/**
 * This shows the order from which projects are built
 **/
public class BuildOrderDFS {

    public static void main(String[] args) {

        char[] projects = {'a', 'b', 'c', 'd', 'e', 'f'};
        char[][] dependencies = {
                new char[]{'a', 'd'},
                new char[]{'f', 'b'},
                new char[]{'b', 'd'},
                new char[]{'f', 'a'},
                new char[]{'b', 'c'},
                new char[]{'c', 'e'},

        };

        HashMap<Integer, Project> projectHashMap = formatProjects(projects, dependencies);
        AdjacencyListGraph<Project> graph = buildGraph(projectHashMap);
        Deque<Project> projectDeque = buildOrderUsingTopSort(graph);
        if (projectDeque == null) {
            System.out.println("There is no build order");
        } else {
            StringBuilder stringBuilder = new StringBuilder(projectDeque.size());
            Project v = projectDeque.pop();
            for (; !projectDeque.isEmpty(); v = projectDeque.pop()) {
                stringBuilder.append(v.key).append(", ");
            }
            stringBuilder.append(v.key).append(", ");

            System.out.println("The build order = " + stringBuilder.toString());
        }
    }

    static int getValueInRange(char value) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int v = Character.getNumericValue(value);

        if (a <= v && v <= z) {
            return v - a;
        } else {
            return -1;
        }
    }

    static HashMap<Integer, Project> formatProjects(char[] projects, char[][] dependencies) {
        HashMap<Integer, Project> projectHashMap = new HashMap<>(projects.length);
        //add project nodes
        for (char proj : projects) {
            int key = getValueInRange(proj);
            projectHashMap.put(key, new Project(key));
        }

        for (char[] dependency : dependencies) {
            int id = getValueInRange(dependency[0]);
            if (projectHashMap.containsKey(id)) {

                int id2 = getValueInRange(dependency[1]);
                Project project1 = projectHashMap.get(id);
                Project project2 = projectHashMap.get(id2);
                project1.addDependency(project2);

            }
        }


        return projectHashMap;
    }

    static AdjacencyListGraph<Project> buildGraph(HashMap<Integer, Project> projects) {
        AdjacencyListGraph<Project> projectAdjacencyListGraph = new AdjacencyListGraph<>(true);

        for (Integer key : projects.keySet()) {
            Project project = projects.get(key);
            if (!project.dependencies.isEmpty()) {
                for (Project project1 : project.dependencies) {
                    //in the build order, there is no weight so the default weight is 1
                    projectAdjacencyListGraph.addEdges(project.key, project1.key, 1, project, project1);
                }
            } else {
                projectAdjacencyListGraph.addVertex(key, project);
            }
        }

        return projectAdjacencyListGraph;
    }

    static Deque<Project> buildOrderUsingTopSort(AdjacencyListGraph<Project> projectAdjacencyListGraph) {
        Deque<Project> stack = new ArrayDeque<>();
        Map<Long, State> states = new HashMap<>();
        //initialize all nodes as unvisited
        initializedStates(states, projectAdjacencyListGraph);
        int ERROR = -1;
        int result = 0;

        for (long projectVertex : projectAdjacencyListGraph.getVertices().keySet()) {
            Vertex<Project> vertex = projectAdjacencyListGraph.getVertices().get(projectVertex);
            //perform dfs on the random project
            if (states.get(projectVertex) == State.UNVISITED) {
                if (!dfs(vertex, stack, states)) {
                    result = ERROR;
                    break;
                }
            }
        }

        return result == ERROR ? null : stack;
    }

    static boolean dfs(Vertex<Project> project, Deque<Project> stacks, Map<Long, State> states) {
        //visiting the project
        states.put(project.getId(), State.VISITING);
        //visit the dependencies
        for (Vertex<Project> dep : project.getAdjacentVerts()) {

            //check for cyclic dependency build
            if (states.get(dep.getId()) == State.VISITING) {
                return false;
            }

            if (states.get(dep.getId()) == State.UNVISITED) {
                if (!dfs(dep, stacks, states))
                    return false;
            }

        }

        //completed the project build
        states.put(project.getId(), State.VISITED);
        //add this to the build order
        stacks.addLast(project.getData());
        return true;
    }

    static void initializedStates(Map<Long, State> states, AdjacencyListGraph<Project> projectAdjacencyListGraph) {
        for (Long key : projectAdjacencyListGraph.getVertices().keySet()) {
            states.put(key, State.UNVISITED);
        }
    }

}

class Project {
    long key;
    List<Project> dependencies;

    Project(long key) {
        this.key = key;
        this.dependencies = new ArrayList<>();
    }

    void addDependency(Project project) {
        dependencies.add(project);
    }
}

/**
 * We can also use another algorithm depending on the
 * minimum incoming edges. OutGoing edge means the project
 * has dependencies and no incoming edge means the project
 * no dependencies.
 * We start to build project with no dependency and this is a
 * project with no incoming edge
 **/

class BuildOrderIncomingEdge {

    public static void main(String[] args) {
        char[] projects = {'a', 'b', 'c', 'd', 'e', 'f'};
        char[][] dependencies = {
                new char[]{'d', 'a'},
                new char[]{'b', 'f'},
                new char[]{'d', 'b'},
                new char[]{'a', 'f'},
                new char[]{'c', 'b'},
                new char[]{'e', 'c'},

        };

        HashMap<Integer, List<Project>> parents = new HashMap<>();
        HashMap<Integer, Project> projectHashMap = preprocessData(projects, dependencies, parents);
        Deque<Project> projectDeque = buildProjectOrder(projectHashMap, parents);
        if (projectDeque == null) {
            System.out.println("There is no build order");
        } else {
            StringBuilder stringBuilder = new StringBuilder(projectDeque.size());
            Project v = projectDeque.pop();
            for (; !projectDeque.isEmpty(); v = projectDeque.pop()) {
                stringBuilder.append(v.key).append(", ");
            }
            stringBuilder.append(v.key).append(", ");

            System.out.println("The build order = " + stringBuilder.toString());
        }
    }

    static int getValueInRange(char value) {
        int a = Character.getNumericValue('a');
        int z = Character.getNumericValue('z');
        int v = Character.getNumericValue(value);

        if (a <= v && v <= z) {
            return v - a;
        } else {
            return -1;
        }
    }


    //preprocess the data to conform with the visioned data format
    static HashMap<Integer, Project> preprocessData(char[] projects, char[][] deps, HashMap<Integer, List<Project>> parents) {
        HashMap<Integer, Project> projectHashMap = new HashMap<>(projects.length);

        //load all projects
        for (char pro : projects) {
            int formattedItem = getValueInRange(pro);
            parents.put(formattedItem, new ArrayList<>());
            projectHashMap.put(formattedItem, new Project(formattedItem));
        }

        //set up the dependencies
        for (char[] dep : deps) {
            int formattedItem = getValueInRange(dep[0]);
            if (projectHashMap.containsKey(formattedItem)) {
                int formattedMaxItem = getValueInRange(dep[1]);
                Project minLevelProject = projectHashMap.get(formattedItem);
                Project maxLevelProject = projectHashMap.get(formattedMaxItem);
                minLevelProject.addDependency(maxLevelProject);
                parents.get(formattedMaxItem).add(minLevelProject);
            }

        }


        return projectHashMap;
    }

    //We will use Indexed Priority Queue based on heaps for
    // getting the minimum edges at all time
    static Deque<Project> buildProjectOrder(HashMap<Integer, Project> projectHashMap, HashMap<Integer, List<Project>> parents) {
        //We use a binaryMinHeap as indexed priority queue
        BinaryMinHeap<Project> ipq = new BinaryMinHeap<>();
        Deque<Project> collectionOrder = new ArrayDeque<>(projectHashMap.size());
        //add the projects into the queue
        for (Integer key : projectHashMap.keySet()) {
            Project project = projectHashMap.get(key);
            ipq.add(project.dependencies.size(), project);
        }

        while (!ipq.empty()) {
            Project checkProj = ipq.min();
            int key = (int) checkProj.key;
            int minWeight = ipq.getWeight(checkProj);
            if (minWeight != 0) {
                return null;
            } else {
                Project project = ipq.extractMin();

                //add this with no dependency to the collection order
                collectionOrder.addFirst(project);

            }


            //relax the dependencies
            for (Project dep : parents.get(key)) {

                if (ipq.containsData(dep)) {
                    //reduce the dependency by 1
                    Integer oldWeight = ipq.getWeight(dep);
                    ipq.decrease(dep, oldWeight - 1);
                }
            }

        }

        return collectionOrder;
    }
}