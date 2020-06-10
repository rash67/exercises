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

public class Node
{
    private final List<Node> children = new ArrayList<>();
    private final String id;

    public Node(String id)
    {
        this.id = id;
    }

    public Node addChildren(Node... children)
    {
        addChildren(Arrays.<Node>asList(children));

        return this;
    }

    public Node addChildren(List<Node> childrenList)
    {
        this.children.addAll(childrenList);

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
