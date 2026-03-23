//import NxWelcome from "./nx-welcome";
//import { Route, Routes, Link } from 'react-router-dom';

import styles from './app.module.css';
import VerticalContainer from '../vertical-container/VerticalContainer'
import './app.css';
import React from 'react';
import {GymRecord} from '../entities/GymRecord';
import ReadContentBox from '../content-box/ReadContentBox';
import CreateContentBox from '../content-box/CreateContentBox';
import DeleteContentBox from '../content-box/DeleteContentBox';
import UpdateContentBox from '../content-box/UpdateContentBox';

export function App() {
    const [records, setRecords] = React.useState<GymRecord[]>([]);

    React.useEffect(() => {
        fetch("http://localhost:8080/gym/records",{
            method: "GET"
            }).then(response => {
                if (response.status == 200){
                    return response.json();
                    }
                return null;
                }).then(data => {
                    if(data !== null){
                        setRecords(data);
                        }
                    })
        }, []);

    const handleCreateSubmit = (exercise: string, weight: number ) => {
        fetch("http://localhost:8080/gym/records",{
            method: "POST",
            headers: {"content-type": "application/json"},
            body: JSON.stringify({exercise: exercise, weight: weight})
            }).then(response => {
                if(response.status == 201){
                    return response.json()
                }
                return null;
            }).then(data =>{
                if(data !== null){
                    setRecords([...records,data])
                }
            })
        };

    const handleDeleteSubmit = (id: number ) => {
        fetch(`http://localhost:8080/gym/records/${id}`,{
            method: "DELETE",
            }).then(response => {
                if(response.status == 200){
                    return response.json()
                }
                return null;
            }).then(data =>{
                if(data !== null){
                    setRecords(records.filter(record => record.id !== data.id));
                }
            });
        };


    const handleUpdateSubmit = (recordToUpdate: GymRecord)=> {
        fetch(`http://localhost:8080/gym/records/${recordToUpdate.id}`,{
         method: "PUT",
         headers: {"content-type": "application/json"},
         body: JSON.stringify({exercise: recordToUpdate.exercise, weight: recordToUpdate.weight})
         }).then(response => {
             if(response.status == 200){
                 return response.json()
             }
             return null;
         }).then(data =>{
             if(data !== null){
                 setRecords(records.map(record => record.id === data.id ?
                     {...record, exercise: data.exercise, weight: data.weight}
                     : record));
             }
         });

        };

  return (
    <div className="main-component">
        <div>
            <VerticalContainer>
            {
               <div>
                    <h2>Create</h2>
                    <CreateContentBox onSubmit={handleCreateSubmit} />
               </div>
            }
            </VerticalContainer>
        </div>

        <div>
            <VerticalContainer>
               <div>
               <h2>Read</h2>
               {
                    records.map(record => <ReadContentBox
                        key={`${record.id}-${record.exercise}-${record.weight}`}
                        content={record}
                    />)
               }
               </div>
            </VerticalContainer>
        </div>

        <div>
            <VerticalContainer>
               <div>
               <h2>Update</h2>
                {
                    records.map(record => <UpdateContentBox
                        key={`${record.id}-${record.exercise}-${record.weight}`}
                        onSubmit={handleUpdateSubmit}
                        content= {record}

                    />)
               }
               </div>
            </VerticalContainer>
        </div>

        <div>
            <VerticalContainer>
               <div>
               <h2>Delete</h2>
               {
                    records.map(record => <DeleteContentBox
                        key={`${record.id}-${record.exercise}-${record.weight}`}
                        onSubmit={handleDeleteSubmit}
                        content= {record}

                    />)
               }
               </div>
            </VerticalContainer>
        </div>

    </div>
  );
}

export default App;


