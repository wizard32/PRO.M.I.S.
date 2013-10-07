/**
 * @author Vasileios Vlachos<br> 
 */

/*
Copyright (C) Vasileios Vlachos, Vassiliki Vouzi
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
with the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies
of the Software, and to permit persons to whom the Software is furnished to do
so, subject to the following conditions:

	* Redistributions of source code must retain the above copyright notice,
this list of conditions and the following disclaimers.
	* Redistributions in binary form must reproduce the above copyright notice,
this list of conditions and the following disclaimers in the documentation
and/or other materials provided with the distribution.
	* Neither the names of <Name of Development Group, Name of Institution>, nor
the names of its contributors may be used to endorse or promote products derived
from this Software without specific prior written permission.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE CONTRIBUTORS
OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS WITH THE SOFTWARE.
*/

/**
 * Class Module is a subsidiary class used in conjuction with class Graph Explorer and
 * is used for keeping track of the number of connections of each node.
 */
public class Module {
	/**
	 * Node name/ID
	 */
    public int moduleId;
	/**
	 * Number of connections of each node
	 */
    public int moduleSum;

	/**
	 * Creates a new instance of Module class by using as parameters the node name/ID.
	 */
    public Module(int moduleId) {
        this.moduleId = moduleId;
        this.moduleSum = 0;
    }
	
	/**
	 * Creates a new instance of Module class by using as parameters an Object.
	 */
    public Module(Object o) {

    }
	
	/**
	 * Creates a new instance of Module class by not using parameters.
	 */
    public Module() {

    }
}
