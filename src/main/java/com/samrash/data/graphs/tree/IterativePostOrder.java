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

package com.samrash.data.graphs.tree;

import com.samrash.data.graphs.Node;
import com.samrash.data.graphs.NodeFunction;

import java.util.Stack;

public class IterativePostOrder
{
    private IterativePostOrder() {}

    public static void postOrderTraversal(Node root, NodeFunction function)
    {
        Stack<Node> toVisit = new Stack<>();
        Stack<Node> entered = new Stack<>();

        toVisit.push(root);

        while (!toVisit.isEmpty()) {
            Node currentNode = toVisit.peek();

            if (currentNode.isLeaf()) {
                function.visit(currentNode);
                toVisit.pop();
            }
            else if (!entered.isEmpty() && entered.peek().getId().equals(currentNode.getId())) {
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
