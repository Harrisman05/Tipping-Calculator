package com.hharris.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/*
Activity means one screen in android terminology, contains business
logic of the app.

onCreate is automatically invoked when the application is started.

setContentView(R.layout.activity.main) - this means:

    - The content (UI) of the MainActivity (screen) should be set to
      R.layout.activity.main (R means resources, also activity.main is xml file)

 */

/*

ConstraintLayout allows you to create large and complex layout with a flat view hierachy
(no nested view groups).

ConstraintLayout is the parent element of the TextView child

When creating components in activity.xml, they must be constrained vertically and horizontally
otherwise there will be a compiler error. For the first component, drag the constraint circles
of the component to the edge of the screen to constrain them. Then add margin to offset them
from the side of the screen in the constraint widget.

Drag anchor points to constrain/group them to other components, then set the offset values in
constraint widget with margin


 */
// logic

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}