package com.desirecodes.myapplication.extention

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE

/**
 * Created by Janith on 18/01/18.
 */
fun FragmentManager.removeFragment(
    tag: String,
    slideIn: Int = 0,
    slideOut: Int = 0,
    fadeIn: Int = 0/*R.animator.fade_in*/,
    fadeOut: Int = 0/*R.animator.fade_out*/
) {
    this.beginTransaction()
        .disallowAddToBackStack()
        .setCustomAnimations(slideIn, slideOut, fadeIn, fadeOut)
        .remove(this.findFragmentByTag(tag)!!)
        .commitNow()
}

fun FragmentManager.addFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = 0,
    slideOut: Int = 0,
    fadeIn: Int = 0/*R.animator.fade_in*/,
    fadeOut: Int = 0/*R.animator.fade_out*/
) {
    this.beginTransaction()
        .addToBackStack(tag)
        .setCustomAnimations(slideIn, slideOut, fadeIn, fadeOut)
        .add(containerViewId, fragment, tag)
        .commit()
}

fun FragmentManager.replaceFragment(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = 0,
    slideOut: Int = 0,
    fadeIn: Int = 0/* R.animator.fade_in*/,
    fadeOut: Int = 0/*R.animator.fade_out*/
) {
    this.beginTransaction()
        .setCustomAnimations(slideIn, slideOut, fadeIn, fadeOut)
        .replace(containerViewId, fragment, tag)
        .addToBackStack(tag)
        .commit()
}

fun FragmentManager.currentFragment(containerViewId: Int) =
    this.findFragmentById(containerViewId)

fun FragmentManager.replaceFragmentWithBackStack(
    containerViewId: Int,
    fragment: Fragment,
    tag: String,
    slideIn: Int = 0,
    slideOut: Int = 0,
    fadeIn: Int = 0/*R.animator.fade_in*/,
    fadeOut: Int = 0/*R.animator.fade_out*/
) {
    val fragmentTransaction = this.beginTransaction()
    fragmentTransaction.replace(containerViewId, fragment)
    // 1. Know how many fragments there are in the stack
    val count = this.backStackEntryCount
    // 2. If the fragment is **not** "home type", save it to the stack
    fragmentTransaction.addToBackStack("other_fragment")
    // Commit !
    fragmentTransaction.commit()
    // 3. After the commit, if the fragment is not an "home type" the back stack is changed, triggering the
    // OnBackStackChanged callback
    this.addOnBackStackChangedListener(object : FragmentManager.OnBackStackChangedListener {
        override fun onBackStackChanged() {
            // If the stack decreases it means I clicked the back button
            if (this@replaceFragmentWithBackStack.backStackEntryCount <= count) {
                // pop all the fragment and remove the listener
                this@replaceFragmentWithBackStack.popBackStack(
                    "other_fragment",
                    POP_BACK_STACK_INCLUSIVE
                )
                this@replaceFragmentWithBackStack.removeOnBackStackChangedListener(this)
                // set the home button selected
            }
        }
    })
}
