package samrash.tree;

import java.util.*;

public class IterativePostOrder
{
	public interface NodeFunction
	{
		public void visit(Node n);
	}

	public static void postOrderTraversal(Node root, NodeFunction function)
	{
		Stack<Node> toVisit = new Stack<Node>();
		Stack<Node> entered = new Stack<Node>();

		toVisit.push(root);

		while (!toVisit.isEmpty()) {
			Node currentNode = toVisit.peek();

			if (currentNode.isLeaf()) {
				function.visit(currentNode);
				toVisit.pop();				
			}
			else if (!entered.isEmpty() && entered.peek().id.equals(currentNode.id)) {
				function.visit(currentNode);
				toVisit.pop();
				entered.pop();				
			}
			else {
				entered.push(currentNode);

				for (Node child : currentNode.getChildren()) {
					toVisit.push(child);
				}
			}
		}
	}

	private static class Node
	{
		private final List<Node> children = new ArrayList<Node>();
		private final String id;

		private Node(String id)
		{
			this.id = id;
		}

		public Node addChildren(Node... children)
		{
			this.children.addAll(Arrays.asList(children));

			return this;
		}

		public List<Node> getChildren()
		{
			return children;
		}

		public String getId()
		{
			return id;
		}

		public boolean isLeaf()
		{
			return children.isEmpty();
		}

		@Override
		public String toString()
		{
			return "Node{" +
				"id=" + id +
				'}';
		}
	}

	public static void main(String[] args)
	{
		Node root = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");
		Node f = new Node("f");
		Node g = new Node("g");
		Node h = new Node("h");
		Node i = new Node("i");
		Node j = new Node("j");

		root.addChildren(
			b.addChildren(
				d.addChildren(f),
				e.addChildren(g,h),
				i.addChildren(j)
			),
			c
		);

		postOrderTraversal(root, new NodeFunction()
		{
			@Override
			public void visit(Node n)
			{
				System.err.println(n);
			}
		});
	}
}