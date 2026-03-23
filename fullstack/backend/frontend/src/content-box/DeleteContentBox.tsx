import React from 'react';
import './ContentBox.css';

import {GymRecord} from '../entities/GymRecord';

interface ContentBoxProps {
        onSubmit: (id: number) => void;
        content: GymRecord;
    }


const DeleteContentBox: React.FC<ContentBoxProps> = ({ onSubmit, content }) => {
    const [record, setRecord] = React.useState<GymRecord>(content);

    const handleSubmit = ()=>{
        onSubmit(record.id);
        }

    return (
        <div className="content-box">
            <p><strong>Exercise:</strong> {record.exercise}</p>
            <p><strong>Weight:</strong> {record.weight} kg</p>
            <button className="delete-btn" onClick={handleSubmit}>Delete</button>
        </div>
        );
}

export default DeleteContentBox;
