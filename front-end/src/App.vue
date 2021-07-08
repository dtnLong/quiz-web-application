<template>
    <main class="relative grid w-full h-full bg-cover place-items-center main-bg">
              
        <!------ Login Form ----->
        <router-view/>

        
    </main>
</template>

<script>
import Home from './views/Home.vue';
import axios from 'axios';
import {gsap} from 'gsap';
import { onMounted } from '@vue/runtime-core';
    export default {
        components: {Home},

        setup(){
            onMounted (() => {
                
            })
        }
    }

</script>

<style scoped>
    .main-bg{
        background: url('./assets/background.svg') center center no-repeat;
        background-size: cover;
    }
    .color{
        animation: blob 4s infinite;
        
    }
    .animation-delay-200{
        animation-delay: 3s;
    }
    /* @keyframes blob {
        0%{
           transform: scale(1);
        }

        50%{
            transform: scale(1.3);
        }

        70%{
            transform: scale(0.7);
        }

        100%{
            transform: scale(1);
        }
    } */
</style>