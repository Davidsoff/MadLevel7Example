package nl.soffware.madlevel7example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_quiz.*
import nl.soffware.madlevel7example.viewmodel.QuizViewModel

class QuizFragment : Fragment(){
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quiz, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeQuiz()
    }

    private fun observeQuiz() {
        viewModel.quiz.observe(viewLifecycleOwner, Observer {
            val quiz = it
            tvQuestion.text = quiz.question

            btnConfirmAnswer.setOnClickListener {
                if (viewModel.isAnswerCorrect(etAnswer.text.toString())) {
                    Toast.makeText(context, "Your answer is correct!", Toast.LENGTH_LONG).show()
                    findNavController().popBackStack()
                } else {
                    Toast.makeText(
                        context,
                        "Your answer is incorrect! The correct answer was: ${quiz.answer}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

}
