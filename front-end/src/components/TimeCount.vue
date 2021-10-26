<template>

    <div class="flex items-center justify-center gap-8" >
        <div class="w-10 text-error-800" id="hour-glass-animation"></div>
        <p class="relative text-4xl font-bold tracking-wide select-none transform text-error-800 -translate-x-1/4">{{timeDisplay}}</p>
    </div>
    

    
</template>

<script>
import lottie from "lottie-web"; 
import LottieAnimation from 'lottie-vuejs'
import {onMounted, ref, computed} from 'vue';
import  AnimationData from '../assets/hour-glass-animation.json';
export default {
    components: {LottieAnimation},
    emits: ['toSubmit'],
    setup(props, {emit}) {

        const QUIZ_DURATION = ref(1800); //in seconds
    
        const timeDisplay = computed(() => 
            {
                const minutes = String(parseInt(QUIZ_DURATION.value/60));
                const seconds = String(QUIZ_DURATION.value % 60);
                const paddedMinutes = ("0" + minutes).slice(-2);
                const paddedSeconds = ("0" + seconds).slice(-2);
                return `${paddedMinutes}:${paddedSeconds}`;
            }
           
        )
        
        
        onMounted(() =>{
            
            lottie.loadAnimation({
                container: document.getElementById("hour-glass-animation"), 
                renderer: "svg",
                loop: true,
                autoplay: true,
                
                animationData: AnimationData,
            })
        

            let timeInterval = setInterval(()=> {
                if (QUIZ_DURATION.value>0){
                    QUIZ_DURATION.value -=1;
                }else{

                    emit("toSubmit", true);
                    clearInterval(timeInterval);
                }
                

            }, 1000) 
        
        })


        
        return {timeDisplay}


    },
}
</script>