<template>
    <span class="absolute cursor-pointer top-10 left-24" @click="backToQuiz" >Back to Quiz</span>
    <section>
        <h1 class='text-3xl font-bold text-center text-gray-50'>Submit Results</h1>
        <p class='mb-12 text-gray-50'> Check your answers before submitting. Once submitted, another version of this will be sent to your team email at group123@gmail.com:</p>

        <div class="z-10 flex flex-col items-center w-9/12 mx-auto from-gray-300 pb-14 text-gray-50 rounded-xl bg-gradient-to-br to-gray-400 pt-9 h-[65vh] overflow-y-scroll">
            <div v-for="({questionNumber, questionText, choiceText, answer}) in submitPayLoad.answers" :key="questionNumber">
                <p>{{questionNumber}}. {{questionText}}</p>
                <p>
                    Answer: {{answer}}. {{choiceText}}
                </p>
            </div>
        </div>

        <button class="inline-block py-3 text-xl font-bold rounded-full px-7 bg-gradient-to-r from-red-600 to-pink-600 " @click="submit">Submit</button>
    </section>
</template>

<script>
import {useRoute} from 'vue-router'
import QuizAPI from '../services/QuizAPI';
import {onMounted} from 'vue';
export default {
    props:{
        submitPayLoad: Object, 
        formattedPayLoad: Object},
    emits: ["backToQuiz"],
    setup( props, {emit}) {
        // console.log(props.submitPayLoad)
        const backToQuiz = () => {
            emit("backToQuiz", false)
        }; 
        
        // const formattedAnwser = () => {
        //     return props.submitPayLoad.answers.map((item) => ({
        //         answer: item.answer === 'N/A'? null: item.answer,
        //         questionNumber: item.questionNumber
        //     }))
        // }


        // const formattedPayLoad = {...props.submitPayLoad, answers: formattedAnwser()}
        // console.log(formattedPayLoad);

        const submit = () => {
            QuizAPI.submitQuiz(props.formattedPayLoad)
            .then(response => console.log(response))
            .catch(error => console.log(error.response))
        }
        
        window.onbeforeunload = confirmExit;
        function confirmExit() {
            submit();
            return "Are you sure you want to exit?"
        }
        
        return{backToQuiz, submit}
    },
}
</script>