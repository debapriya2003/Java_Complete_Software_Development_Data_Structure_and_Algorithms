// Below is a **clean, theory-focused explanation**, exactly in the style used in standard DSA notes and interviews.

// ---

// ## Requirements Needed to Construct a **Unique Binary Tree**

// ---

// ### 1. Introduction

// Constructing a binary tree means **rebuilding the exact structure of the tree** from given traversal data.

// However, **not every traversal combination guarantees uniqueness**.

// > 🔑 **Uniqueness depends on how much structural information the traversals provide.**

// ---

// ### 2. Why Traversals Matter

// Each traversal gives **partial information**:

// * **Inorder** → tells relative left/right positioning
// * **Preorder** → tells root first
// * **Postorder** → tells root last
// * **Level order** → tells level-wise structure

// To uniquely identify a tree, we must know:

// 1. **Which node is root**
// 2. **Which nodes belong to left subtree**
// 3. **Which nodes belong to right subtree**

// ---

// ### 3. Traversal Combinations and Uniqueness

// #### ✅ **1. Inorder + Preorder → Unique Binary Tree**

// **Why it works:**

// * Preorder gives the **root**
// * Inorder splits nodes into **left and right subtrees**

// **Process intuition:**

// 1. First element of preorder = root
// 2. Find root in inorder
// 3. Left of root → left subtree
// 4. Right of root → right subtree
// 5. Recursively apply

// ✔ **Guarantees a unique binary tree**

// ---

// #### ✅ **2. Inorder + Postorder → Unique Binary Tree**

// **Why it works:**

// * Postorder gives the **root**
// * Inorder splits nodes into **left and right subtrees**

// **Process intuition:**

// 1. Last element of postorder = root
// 2. Locate root in inorder
// 3. Split inorder into left/right
// 4. Recursively build subtrees

// ✔ **Guarantees a unique binary tree**

// ---

// #### ❌ **3. Preorder + Postorder → NOT Unique (in general)**

// **Why it fails:**

// * Both traversals give root info
// * Neither tells where left subtree ends and right begins

// **Example:**

// ```
// Preorder  : A B
// Postorder : B A
// ```

// Possible trees:

// ```
// A          A
//  \   or   /
//   B      B
// ```

// ❌ **Multiple trees possible → not unique**

// ---

// #### ❌ **4. Inorder alone → NOT Unique**

// Inorder only shows relative ordering, **not structure**.

// Example:

// ```
// Inorder: B A C
// ```

// Possible trees:

// * A as root
// * B as root
// * C as root

// ❌ **Not unique**

// ---

// #### ❌ **5. Preorder alone / Postorder alone → NOT Unique**

// Only tells root position, **no subtree boundaries**.

// ❌ **Not unique**

// ---

// ### 4. Special Case: Full Binary Tree

// > A **Full Binary Tree** has either **0 or 2 children** per node.

// #### ✅ **Preorder + Postorder + Full Tree Condition → Unique**

// **Why?**

// * Full tree property removes ambiguity
// * Each internal node must have exactly two children

// ✔ **Uniqueness is guaranteed ONLY with this extra condition**

// ---

// ### 5. Level Order Traversal

// #### ❌ **Level Order alone → NOT Unique**

// Because children positions are unknown.

// #### ⚠️ **Level Order + Inorder**

// * Can form a unique tree
// * But algorithm is complex and rarely asked

// ---

// ### 6. Summary Table (Very Important for Interviews)

// | Traversals Given                 | Unique Tree? | Reason                  |
// | -------------------------------- | ------------ | ----------------------- |
// | Inorder + Preorder               | ✅ Yes        | Root + left/right split |
// | Inorder + Postorder              | ✅ Yes        | Root + left/right split |
// | Preorder + Postorder             | ❌ No         | No boundary info        |
// | Inorder only                     | ❌ No         | No root info            |
// | Preorder only                    | ❌ No         | No subtree split        |
// | Postorder only                   | ❌ No         | No subtree split        |
// | Preorder + Postorder + Full Tree | ✅ Yes        | Structure constraint    |

// ---

// ### 7. Key Interview Takeaways

// * **Inorder is mandatory** for uniqueness (unless tree is full)
// * Root identification alone is **not sufficient**
// * Tree constraints (Complete / Full / BST) **change rules**
// * This theory is the base for:

//   * Tree construction problems
//   * Serialization & deserialization
//   * Compiler syntax trees

// ---

// ### 8. One-Line Rule (Easy to Remember)

// > ✅ **Inorder + (Preorder or Postorder) = Unique Binary Tree**

// ---

// If you want, next I can cover:

// * **Construct Binary Tree from Inorder & Preorder**
// * **Construct Binary Tree from Inorder & Postorder**
// * **Why Preorder + Postorder fails (visual proof)**
// * **BST construction rules**

// Just tell me 🌳
