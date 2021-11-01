<template>
    
    <section class="relative h-auto select-none py-36">
        <img src="../assets/logis-base-logo.png" class="fixed w-20 transform -translate-x-1/2 md:w-40 left-1/2 top-11" alt="the-logisticom-logo">
        
             <h1 class='relative text-4xl font-bold text-center text-error-900'>Submitted <span class="absolute inline-block w-32 -top-1/2 left-1/2 " id="confetti-animation"></span>  </h1>
        <p class='px-2 mt-3 mb-10 text-xl font-medium text-center max-w-prose text-error-900'>You have successfully submitted! A preview of your submission has been sent to your team leader's mailbox. Please check and reply to the confirmation email if you received the information well.</p>

        <!-- <div class="w-11/12 h-auto mx-auto overflow-y-scroll text-gray-800 md:w-10/12 lg:w-9/12 py-14 rounded-3xl bg-form-bg">

            <div class='w-11/12 mx-auto md:w-10/12 lg:w-3/4'>
                <p class='mb-2 text-xl font-bold'>Group: <span class='text-error-500'>{{submitPayLoad.groupName}}</span></p>
                <p class='mb-8 text-xl font-bold'>Quiz Code: <span class= 'text-error-500'>{{submitPayLoad.quizCode}} </span></p>

                <div class="flex flex-col gap-4">
                    <div class='text-xl' v-for="({questionNumber, questionText, choiceText, answer}) in submitPayLoad.answers" :key="questionNumber">
                    
                    <question-quiz :questionNumber="questionNumber" :questionText="questionText"></question-quiz>
                    <p class='font-bold'>
                        {{answer}}. {{choiceText}}
                    </p>
                </div>
                </div>
            
            
            </div>

            
            
        </div> -->
        
    
        <!-- <p class="px-10 mt-2 italic font-medium text-center md:px-0">A copy of this preview will be sent to your team leader's mailbox.</p> -->
        
       
    </section>

    
</template>

<script>

import lottie from "lottie-web";
import Confetti from '../assets/confetti.json'


import {onMounted, ref} from 'vue';
export default {
    
    setup( props, {emit}) {
    
        onMounted(() => {
      lottie.loadAnimation({
                container: document.getElementById("confetti-animation"), 
                renderer: "svg",
                loop: false,
                autoplay: true,
                //path: "https://assets7.lottiefiles.com/packages/lf20_xz75lvff.json",
                animationData: Confetti,
            })
    })
        
        
        return{}
    },
}
</script>