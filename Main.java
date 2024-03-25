// worked with ani on this

import java.util.Scanner;

class FamilyTreeNode {
    private FamilyTreeNode parent1;
    private FamilyTreeNode parent2;

    private String name;

    FamilyTreeNode (String name) {
        this.name = name;
        parent1 = null;
        parent2 = null;
    }

    FamilyTreeNode (String name, FamilyTreeNode parent1, FamilyTreeNode parent2) {
        this.name = name;
        this.parent1 = parent1;
        this.parent2 = parent2;
    }

    public String getName() {
        return name;
    }

    public FamilyTreeNode getParent1() {
        return parent1;
    }

    public FamilyTreeNode getParent2() {
        return parent2;
    }

    public void print() {
        if (parent1 == null && parent2 == null) {
            System.out.println("\t" + name);
        } else if (parent1 == null) {
            System.out.println("\t\t" + name);
            System.out.println("\t\t\t\t" + parent2.getName());
            System.out.println("Parent 2 of " + name);
            parent2.print();
        } else if (parent2 == null) {
            System.out.println("\t\t" + name);
            System.out.println("" + parent1.getName() + "\t\t\t");
            System.out.println("Parent 1 of " + name);
            parent1.print();
        } else {
            System.out.println("\t\t" + name);
            System.out.println("\t/\t\t\t\\");
            System.out.println("" + parent1.getName() + "\t\t\t" + parent2.getName());
            System.out.println("Parent 1 of " + name);
            parent1.print();
            System.out.println("Parent 2 of " + name);
            parent2.print();
        }

        System.out.println();
    }

    public int getDepth() {
        if (parent1 == null && parent2 == null) {
            return 1;
        } else {
            return 1 + Math.max(parent1.getDepth(), parent2.getDepth());
        }
    }

    public int getMembersOfFamily() {
        if (parent1 == null && parent2 == null) {
            return 1;
        } else {
            return 1 + parent1.getMembersOfFamily() + parent2.getMembersOfFamily();
        }
    }

}

public class Main {
    // test the Family Tree class
    public static void main(String[] args) {
        System.out.println("Testing the Family binary Tree class");
        // setup grand parents
        FamilyTreeNode grandParent1 = new FamilyTreeNode("Dylan Lad Intwala");
        FamilyTreeNode grandParent2 = new FamilyTreeNode("Vyas Dhar");

        FamilyTreeNode grandParent3 = new FamilyTreeNode("Krish Bansal");
        FamilyTreeNode grandParent4 = new FamilyTreeNode("Krish Shetty");

        // setup parents
        FamilyTreeNode parent1 = new FamilyTreeNode("Anirudh Tiwary", grandParent1, grandParent2);
        FamilyTreeNode parent2 = new FamilyTreeNode("Vibhav Chaturvedi", grandParent3, grandParent4);

        FamilyTreeNode child = new FamilyTreeNode("Akash Dubey", parent1, parent2);
        child.print();
        System.out.println("Depth of the tree: " + child.getDepth());
        System.out.println("Number of members in the family: " + child.getMembersOfFamily());
    }
}