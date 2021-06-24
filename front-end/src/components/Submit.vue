<template>
    <span class="absolute cursor-pointer top-10 left-24" @click="backToQuiz" >Back to Quiz</span>
    <section>
        <h1 class='text-3xl font-bold text-center text-gray-50'>Submit Results</h1>
        <p class='mb-12 text-gray-50'>Check your answers before submitting. Once submitted, another version of this will be sent to your team email at group123@gmail.com:</p>

        <div class="z-10 flex flex-col items-center w-9/12 mx-auto from-gray-300 pb-14 text-gray-50 rounded-xl bg-gradient-to-br to-gray-400 pt-9">
            <div v-for="({questionNumber, questionText, optionText, optionSelected}) in submitPayLoad.data" :key="questionNumber">
                <p>{{questionNumber}}. {{questionText}}</p>
                <p>
                    Answer: {{optionSelected}}. {{optionText}}
                </p>
            </div>
        </div>
    </section>
</template>

<script>
import {useRoute} from 'vue-router'
export default {
    props:{submitPayLoad:Object},
    emits: ["backToQuiz"],
    setup( props, {emit}) {
        const backToQuiz = () => {
            emit("backToQuiz", false)
        }
        return{backToQuiz}
    },
}
</script>