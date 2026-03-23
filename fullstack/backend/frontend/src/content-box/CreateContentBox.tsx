import React from 'react';
import './ContentBox.css';

import {GymRecord} from '../entities/GymRecord';

interface ContentBoxProps {
        onSubmit: (exercise: string, weight: number) => void;
    }


const CreateContentBox: React.FC<ContentBoxProps> = ({ onSubmit }) => {
    const [exercise, setExercise] = React.useState("");
    const [weight, setWeight] = React.useState<number>();

    const handleSubmit = ()=>{
        onSubmit(exercise,weight || 0);
        setExercise("");
        setWeight(0);
        }

    return (
        <div className="content-box">
            <input
                type="text"
                value={exercise}
                onChange = {(e) => setExercise(e.target.value)}
                placeholder="Exercise Name"
            />
            <input
                type="number"
                value={weight}
                onChange = {(e) => setWeight(parseInt(e.target.value))}
                placeholder="Weight in kg"
            />
            <button className="create-btn" onClick={handleSubmit}>Create</button>
        </div>
        );
}

export default CreateContentBox;
