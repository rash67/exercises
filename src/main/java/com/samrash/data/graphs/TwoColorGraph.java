/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.samrash.data.graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TwoColorGraph
{
    private TwoColorGraph() {}

    public interface NodeFunction
    {
        void visit(Node n);
    }

    public static void postOrderTraversal(Node root, NodeFunction function)
    {
        Stack<Node> toVisit = new Stack<>();
        Stack<Node> entered = new Stack<>();
//        Stack<Node> exited = new Stack<>();

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
        private final List<Node> children = new ArrayList<>();
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

        root.addChildren(b.addChildren(d.addChildren(f), e.addChildren(g, h), i.addChildren(j)), c);
        postOrderTraversal(root, System.err::println);
    }
}
