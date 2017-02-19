package com.company;

import com.company.command.InvalidCommandException;

import java.util.*;

/**
 * Created by dengw on 2/17/2017.
 */
public class Graph {

    static class Component {
        String name;
        Set<Component> dependOn;
        Set<Component> beenDependOn;
        boolean installed;
        boolean explicitlyInstalled;

        public Component(String name) {
            this.name = name;
            this.dependOn = new HashSet<>();
            this.beenDependOn = new HashSet<>();
        }
    }

    Map<String, Component> components;
    Set<Component> installedComponents;

    public Graph() {
        components = new HashMap<>();
        installedComponents = new HashSet<>();
    }

    public synchronized void makeDependencies(String name, List<String> dependOn) {
        Component cmp = components.get(name);
        if (cmp == null) {
            cmp = new Component(name);
            components.put(name, cmp);
        }

        for (String dep : dependOn) {
            Component d = components.get(dep);
            if (d == null) {
                d = new Component(dep);
                components.put(dep, d);
            }

            cmp.dependOn.add(d);
            d.beenDependOn.add(cmp);
        }
    }

    public synchronized void install(String name, boolean explicitlyInstalled) throws InvalidCommandException {
        Component cmp = components.get(name);
        if (cmp == null) {
            throw new InvalidCommandException("Component not in system: " + name);
        }

        if (!cmp.installed) {
            for (Component dep : cmp.dependOn) {
                install(dep.name, false);
            }
            cmp.installed = true;
            cmp.explicitlyInstalled = explicitlyInstalled;
            installedComponents.add(cmp);
            System.out.println("\tInstalling " + name);
        } else {
            if (explicitlyInstalled) {
                System.out.println("\t" + name + " is already installed.");
            }
        }
    }

    public synchronized void remove(String name, boolean explicitlyRemove) throws InvalidCommandException {
        Component cmp = components.get(name);
        if (cmp == null) {
            throw new InvalidCommandException("Component not in system: " + name);
        }
        if (cmp.explicitlyInstalled && !explicitlyRemove) {
            return;
        }
        if (!cmp.installed) {
            System.out.println("\t" + name + " is not installed.");
            return;
        }

        for (Component cp : cmp.beenDependOn) {
            if (cp.installed) {
                System.out.println("\t" + name + " is still needed.");
                return;
            }
        }

        cmp.installed = false;
        cmp.explicitlyInstalled = false;
        installedComponents.remove(cmp);
        System.out.println("\tRemoving " + name);

        for (Component dep : cmp.dependOn) {
            if (dep.installed && !dep.explicitlyInstalled) {
                boolean needed = false;
                for (Component cp : dep.beenDependOn) {
                    if (cp.installed && !cp.name.equals(name)) {
                        needed = true;
                        break;
                    }
                }
                if (!needed) {
                    remove(dep.name, false);
                }
            }
        }

    }

    public synchronized Set<String> listInstalled() {
        Set<String> result = new HashSet<>();
        for (Component cp : installedComponents) {
            result.add(cp.name);
        }
        return result;
    }
}
